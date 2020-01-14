package cn.ekgc.ytrip.service.impl;

import cn.ekgc.ytrip.dao.*;
import cn.ekgc.ytrip.pojo.entity.*;
import cn.ekgc.ytrip.pojo.vo.*;
import cn.ekgc.ytrip.service.HotelOrderService;
import cn.ekgc.ytrip.util.DateUtil;
import cn.ekgc.ytrip.util.OrderNoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * <b>酒店订单业务层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Service("hotelOrderService")
@Transactional
public class HotelOrderServiceImpl implements HotelOrderService {
	@Autowired
	private HotelOrderDao hotelOrderDao;
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private HotelRoomDao hotelRoomDao;
	@Autowired
	private HotelTempStoreDao hotelTempStoreDao;
	@Autowired
	private LabelDicDao labelDicDao;

	/**
	 * <b>生成订单前,获取预订信息</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public RoomStoreVO getPreorderInfo(ValidateRoomStoreVO vo) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		// 得到当前酒店信息
		queryMap.put("id", vo.getHotelId());
		Hotel hotel = hotelDao.findHotelListByQuery(queryMap).get(0);
		// 得到当前酒店房间信息
		queryMap.put("hotelId", vo.getHotelId());
		queryMap.put("id", vo.getRoomId());
		HotelRoom hotelRoom = hotelRoomDao.findHotelRoomListByQuery(queryMap).get(0);
		// 查询酒店房间库存
		queryMap.put("startTime", vo.getCheckInDate());
		queryMap.put("endTime", vo.getCheckOutDate());
		Integer store = hotelTempStoreDao.findHotelTempStoreByQuery(queryMap);
		// 查询酒店房间的消耗数量
		queryMap.put("orderType", 1);
		queryMap.put("orderStatus", 0);
		Integer count = hotelOrderDao.findHotelOrderCountByQuery(queryMap);
		// 封装返回参数
		RoomStoreVO roomStoreVO = new RoomStoreVO();
		roomStoreVO.setHotelId(vo.getHotelId());
		roomStoreVO.setRoomId(vo.getRoomId());
		roomStoreVO.setHotelName(hotel.getHotelName());
		roomStoreVO.setCount(vo.getCount());
		roomStoreVO.setPrice(hotelRoom.getRoomPrice());
		roomStoreVO.setCheckInDate(vo.getCheckInDate());
		roomStoreVO.setCheckOutDate(vo.getCheckOutDate());
		if (store != null) {
			roomStoreVO.setStore(store - count);
		} else {
			queryMap.put("productType", 1);
			store = hotelTempStoreDao.findTempStore(queryMap);
			roomStoreVO.setStore(store - count);
		}
		return roomStoreVO;
	}

	/**
	 * <b>生成订单</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addHotelOrder(AddHotelOrderVO vo) throws Exception {
		// 封装酒店订单实体类对象
		HotelOrder hotelOrder = new HotelOrder();
		hotelOrder.setUserId(vo.getUserId());
		hotelOrder.setOrderType(vo.getOrderType());
		hotelOrder.setOrderNo(OrderNoUtil.createUserNo(vo.getHotelId(), vo.getRoomId()));
		hotelOrder.setHotelId(vo.getHotelId());
		hotelOrder.setRoomId(vo.getRoomId());
		hotelOrder.setCount(vo.getCount());
		// 获得预订天数
		Date checkInDate = DateUtil.parseDate(vo.getCheckInDate());
		hotelOrder.setCheckInDate(checkInDate);
		Date checkOutDate = DateUtil.parseDate(vo.getCheckOutDate());
		hotelOrder.setCheckOutDate(checkOutDate);
		hotelOrder.setBookingDays((int)((checkOutDate.getTime() - checkInDate.getTime()) / 1000 / 3600 / 24));
		// 点单状态
		hotelOrder.setOrderStatus(0);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", vo.getHotelId());
		Hotel hotel = hotelDao.findHotelListByQuery(queryMap).get(0);
		hotelOrder.setHotelName(hotel.getHotelName());
		// 得到当前酒店房间信息
		queryMap.put("hotelId", vo.getHotelId());
		queryMap.put("id", vo.getRoomId());
		HotelRoom hotelRoom = hotelRoomDao.findHotelRoomListByQuery(queryMap).get(0);
		// 计算订单总金额
		Double payAmount = hotelRoom.getRoomPrice() * hotelOrder.getCount() * hotelOrder.getBookingDays();
		hotelOrder.setPayAmount(payAmount);
		hotelOrder.setNoticePhone(vo.getNoticePhone());
		hotelOrder.setNoticeEmail(vo.getNoticeEmail());
		hotelOrder.setSpecialRequirement(vo.getSpecialRequirement());
		if (vo.getIsNeedInvoice() != null) {
			hotelOrder.setIsNeedInvoice(vo.getIsNeedInvoice());
		}
		hotelOrder.setIsNeedInvoice(0);
		hotelOrder.setInvoiceType(vo.getInvoiceType());
		hotelOrder.setInvoiceHead(vo.getInvoiceHead());
		// 拼接联系人
		StringBuffer sb = new StringBuffer();
		for (UserLinkUser userLinkUser : vo.getLinkUser()) {
			sb.append(userLinkUser.getLinkUserName() + ",");
		}
		hotelOrder.setLinkUserName(sb.toString());
		hotelOrder.setCreatedBy(vo.getUserId());
		hotelOrder.setCreationDate(new Date());
		// 保存用户信息
		if (hotelOrderDao.addHotelOrder(hotelOrder)) {
			// 根据订单编号查询订单信息
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("orderNo", hotelOrder.getOrderNo());
			List<HotelOrder> orderList = hotelOrderDao.findHotelOrderByQuery(paramMap);
			if (orderList != null && orderList.size() > 0) {
				//添加订单之后还需要往订单与常用联系人关联表中添加记录
				OrderLinkUser orderLinkUser = new OrderLinkUser();
				orderLinkUser.setOrderId(orderList.get(0).getId());
				for (int j = 0; j < vo.getLinkUser().size(); j++) {
					orderLinkUser.setLinkUserId(vo.getLinkUser().get(j).getId());
					orderLinkUser.setLinkUserName(vo.getLinkUser().get(j).getLinkUserName());
					hotelOrderDao.addOrderLinkUser(orderLinkUser);
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id",orderList.get(0).getId());
				map.put("orderNo", orderList.get(0).getOrderNo());
				return map;
			}
		}
		return null;
	}

	/**
	 * <b>根据订单ID查看个人订单详情</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public PersonalHotelOrderVO getPersonalOrderInfo(Long orderId) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", orderId);
		// 根据条件查询订单
		List<HotelOrder> hotelOrderList = hotelOrderDao.findHotelOrderByQuery(queryMap);
		HotelOrder hotelOrder = null;
		if (hotelOrderList != null && hotelOrderList.size() > 0) {
			hotelOrder = hotelOrderList.get(0);
		}
		// 封装返回参数对象
		PersonalHotelOrderVO personalHotelOrderVO = new PersonalHotelOrderVO();
		personalHotelOrderVO.setId(hotelOrder.getId());
		personalHotelOrderVO.setOrderNo(hotelOrder.getOrderNo());
		personalHotelOrderVO.setCreationDate(hotelOrder.getCreationDate());
		personalHotelOrderVO.setBookType(hotelOrder.getBookType());
		// 查询订单房间信息
		// 得到当前酒店信息
		queryMap.put("hotelId", hotelOrder.getHotelId());
		// 得到当前酒店房间信息
		queryMap.put("id", hotelOrder.getRoomId());
		HotelRoom hotelRoom = hotelRoomDao.findHotelRoomListByQuery(queryMap).get(0);
		if (hotelRoom != null) {
			personalHotelOrderVO.setRoomPayType(hotelRoom.getPayType());
		}
		Integer orderStatus = hotelOrder.getOrderStatus();
		personalHotelOrderVO.setOrderStatus(orderStatus);
		//订单状态（0：待支付 1:已取消 2:支付成功 3:已消费 4:已点评）
		//{"1":"订单提交","2":"订单支付","3":"支付成功","4":"入住","5":"订单点评","6":"订单完成"}
		//{"1":"订单提交","2":"订单支付","3":"订单取消"}
		if (orderStatus == 0) {
			personalHotelOrderVO.setOrderProcess(1);
			personalHotelOrderVO.setProcessNode("2");   // 订单支付
		} else if (orderStatus == 1) {
			personalHotelOrderVO.setOrderProcess(2);
			personalHotelOrderVO.setProcessNode("3");   // 订单取消
		} else if (orderStatus == 2) {
			personalHotelOrderVO.setOrderProcess(3);
			personalHotelOrderVO.setProcessNode("3");   // 支付成功(未消费)
		} else if (orderStatus == 3) {
			personalHotelOrderVO.setOrderProcess(4);
			personalHotelOrderVO.setProcessNode("5");   // 订单点评
		} else if (orderStatus == 4) {
			personalHotelOrderVO.setOrderProcess(5);
			personalHotelOrderVO.setProcessNode("6");   // 订单完成
		} else {
			personalHotelOrderVO.setOrderProcess(null);
			personalHotelOrderVO.setProcessNode(null);
		}
		personalHotelOrderVO.setPayAmount(hotelOrder.getPayAmount());
		personalHotelOrderVO.setPayType(hotelOrder.getPayType());
		personalHotelOrderVO.setNoticePhone(hotelOrder.getNoticePhone());
 		return personalHotelOrderVO;
	}

	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public PersonalOrderRoomVO getPersonalOrderRoomInfo(Long orderId) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", orderId);
		// 根据条件查询订单
		List<HotelOrder> hotelOrderList = hotelOrderDao.findHotelOrderByQuery(queryMap);
		PersonalOrderRoomVO vo = new PersonalOrderRoomVO();
		if (hotelOrderList != null && hotelOrderList.size() > 0) {
			HotelOrder hotelOrder = hotelOrderList.get(0);
			// 查询酒店信息
			queryMap.put("id", hotelOrder.getHotelId());
			Hotel hotel = hotelDao.findHotelListByQuery(queryMap).get(0);
			// 得到当前酒店房间信息
			queryMap.put("hotelId", hotelOrder.getHotelId());
			queryMap.put("id", hotelOrder.getRoomId());
			HotelRoom hotelRoom = hotelRoomDao.findHotelRoomListByQuery(queryMap).get(0);
			// 封装返回参数对象
			vo.setId(orderId);
			vo.setHotelId(hotelOrder.getHotelId());
			vo.setHotelName(hotelOrder.getHotelName());
			vo.setHotelLevel(hotel.getHotelLevel());
			vo.setAddress(hotel.getAddress());
			vo.setRoomId(hotelOrder.getRoomId());
			vo.setRoomTitle(hotelRoom.getRoomTitle());
			vo.setCheckInDate(hotelOrder.getCheckInDate());
			vo.setCheckOutDate(hotelOrder.getCheckOutDate());
			vo.setCount(hotelOrder.getCount());
			vo.setBookingDays(hotelOrder.getBookingDays());
			vo.setLinkUserName(hotelOrder.getLinkUserName());
			vo.setSpecialRequirement(hotelOrder.getSpecialRequirement());
			vo.setPayAmount(hotelOrder.getPayAmount());
			vo.setRoomPayType(hotelRoom.getPayType());
			vo.setIsHavingBreakfast(hotelRoom.getIsHavingBreakfast());
			// 酒店房间床型
			queryMap.put("id", hotelRoom.getRoomBedTypeId());
			String roomBedTypeName = labelDicDao.findHotelFeatureByQuery(queryMap).get(0).getName();
			vo.setRoomBedTypeName(roomBedTypeName);
		}

		//vo.setCheckInWeek(DayOfWeek);
		return vo;
	}

	/**
	 * <b>根据订单ID获取订单信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public ModifyHotelOrderVO queryOrderById(Long orderId) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", orderId);
		// 根据条件查询订单
		List<HotelOrder> hotelOrderList = hotelOrderDao.findHotelOrderByQuery(queryMap);
		HotelOrder hotelOrder = hotelOrderList.get(0);
		// 创建返回参数对象
		ModifyHotelOrderVO vo = new ModifyHotelOrderVO();
		BeanUtils.copyProperties(hotelOrder, vo);
		// 查询订单联系人列表
		queryMap.put("orderId", orderId);
		List<OrderLinkUser> orderLinkUserList = hotelOrderDao.findOrderLinkUserByQuery(queryMap);
		if (orderLinkUserList != null && orderLinkUserList.size() > 0) {
			List<OrderLinkUserVo> list = new ArrayList<OrderLinkUserVo>();
			for (OrderLinkUser orderLinkUser : orderLinkUserList) {
				OrderLinkUserVo orderLinkUserVo = new OrderLinkUserVo();
				orderLinkUserVo.setLinkUserId(orderLinkUser.getLinkUserId());
				orderLinkUserVo.setLinkUserName(orderLinkUser.getLinkUserName());
				list.add(orderLinkUserVo);
			}
			vo.setOrderLinkUserList(list);
		}
		return vo;
	}

	/**
	 * <b>根据个人订单列表，并分页显示</b>
	 * @param vo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Page<HotelOrder> getPersonalOrderList(SearchOrderVO vo, Long id) throws Exception {
		// 判断用户查询订单状态
		if (vo.getOrderStatus() < 0) {
			vo.setOrderStatus(null);
		}
		if (vo.getOrderType() < 0) {
			vo.setOrderType(null);
		}
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (!"".equals(vo.getOrderNo()) && vo.getOrderNo() != null) {
			queryMap.put("orderNo", vo.getOrderNo());
		}
		if (!"".equals(vo.getLinkUserName()) && vo.getLinkUserName() != null) {
			queryMap.put("linkUserName", "%" + vo.getLinkUserName() + "%");
		}
		if (!"".equals(vo.getStartDate()) && vo.getStartDate() != null) {
			queryMap.put("startDate", vo.getStartDate());
		}
		if (!"".equals(vo.getEndDate()) && vo.getEndDate() != null) {
			queryMap.put("endDate", vo.getEndDate());
		}
		queryMap.put("userId", id);
		queryMap.put("orderStatus", vo.getOrderStatus());
		queryMap.put("orderType", vo.getOrderType());
		Integer total = hotelOrderDao.findHotelOrderByQuery(queryMap).size();
		if (vo.getPageNo() == null) {
			vo.setPageNo(1);
		}
		Integer curpage = vo.getPageNo();
		Integer pagesize = vo.getPageSize();
		Integer beginPos = (curpage -1) * pagesize;
		queryMap.put("begin", beginPos);
		queryMap.put("size", pagesize);
		List<HotelOrder> list = hotelOrderDao.findHotelOrderByQuery(queryMap);
		Page<HotelOrder> page = new Page<>(curpage,pagesize,total);
		page.setRows(list);
		return page;
	}

	/**
	 * <b>扫描中间表,执行库存更新操作</b>
	 * @return
	 * @throws Exception
	 */
//	public boolean scanTradeEnd() throws Exception {
//		// 封装参数集合
//		Map<String, Object> queryMap = new HashMap<String, Object>();
//		queryMap.put("flag", 1);
//		return false;
//	}

	/**
	 * <b>根据条件查询酒店订单</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	public HotelOrder getHotelOrderByNo(String orderNo) throws Exception {
		// 封装参数集合
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("orderNo", orderNo);
		List<HotelOrder> list = hotelOrderDao.findHotelOrderByQuery(queryMap);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * <b>修改订单信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	public boolean updateHotelOrder(HotelOrder hotelOrder) throws Exception {
		boolean flag = hotelOrderDao.updateHotelOrder(hotelOrder);
		if (flag) {
			return true;
		}
		return false;
	}
}
