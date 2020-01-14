package cn.ekgc.ytrip.service;

import cn.ekgc.ytrip.pojo.entity.HotelOrder;
import cn.ekgc.ytrip.pojo.entity.User;
import cn.ekgc.ytrip.pojo.vo.*;

import java.util.List;
import java.util.Map;

/**
 * <b>酒店订单模块业务层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
public interface HotelOrderService {

	/**
	 * <b>生成订单前,获取预订信息</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	RoomStoreVO getPreorderInfo(ValidateRoomStoreVO vo) throws Exception;

	/**
	 * <b>生成订单前</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> addHotelOrder(AddHotelOrderVO vo) throws Exception;

	/**
	 * <b>根据订单ID查看个人订单详情</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	PersonalHotelOrderVO getPersonalOrderInfo(Long orderId) throws Exception;

	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	PersonalOrderRoomVO getPersonalOrderRoomInfo(Long orderId) throws Exception;

	/**
	 * <b>根据订单ID获取订单信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	ModifyHotelOrderVO queryOrderById(Long orderId) throws Exception;

	/**
	 * <b>根据个人订单列表，并分页显示</b>
	 * @param vo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Page<HotelOrder> getPersonalOrderList(SearchOrderVO vo, Long id) throws Exception;

	/**
	 * <b>扫描中间表,执行库存更新操作</b>
	 * @return
	 * @throws Exception
	 */
//	boolean scanTradeEnd() throws Exception;

	/**
	 * <b>根据条件查询酒店订单</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	HotelOrder getHotelOrderByNo(String orderNo) throws Exception;

	/**
	 * <b>修改订单信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	boolean updateHotelOrder(HotelOrder hotelOrder) throws Exception;
}
