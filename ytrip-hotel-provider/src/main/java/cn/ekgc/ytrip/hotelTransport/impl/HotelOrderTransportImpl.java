package cn.ekgc.ytrip.hotelTransport.impl;

import cn.ekgc.ytrip.hotelTransport.HotelOrderTransport;
import cn.ekgc.ytrip.pojo.entity.HotelOrder;
import cn.ekgc.ytrip.pojo.entity.User;
import cn.ekgc.ytrip.pojo.vo.*;
import cn.ekgc.ytrip.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <b>酒店订单模块传输层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@RestController("hotelOrderTransport")
@RequestMapping("/hotelOrder")
public class HotelOrderTransportImpl implements HotelOrderTransport {
	@Autowired
	private HotelOrderService hotelOrderService;

	/**
	 * <b>生成订单前,获取预订信息</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/preorderInfo", method = RequestMethod.POST)
	public RoomStoreVO getPreorderInfo(@RequestBody ValidateRoomStoreVO vo) throws Exception {
		return hotelOrderService.getPreorderInfo(vo);
	}

	/**
	 * <b>生成订单前</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addHotelOrder", method = RequestMethod.POST)
	public Map<String, Object> addHotelOrder(@RequestBody AddHotelOrderVO vo) throws Exception {
		return hotelOrderService.addHotelOrder(vo);
	}

	/**
	 * <b>根据订单ID查看个人订单详情</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPersonalOrderInfo", method = RequestMethod.POST)
	public PersonalHotelOrderVO getPersonalOrderInfo(@RequestParam Long orderId) throws Exception {
		return hotelOrderService.getPersonalOrderInfo(orderId);
	}

	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPersonalOrderRoomInfo", method = RequestMethod.POST)
	public PersonalOrderRoomVO getPersonalOrderRoomInfo(@RequestParam Long orderId) throws Exception {
		return hotelOrderService.getPersonalOrderRoomInfo(orderId);
	}

	/**
	 * <b>根据订单ID获取订单信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrderById", method = RequestMethod.POST)
	public ModifyHotelOrderVO queryOrderById(@RequestParam Long orderId) throws Exception {
		return hotelOrderService.queryOrderById(orderId);
	}

	/**
	 * <b>根据个人订单列表，并分页显示</b>
	 * @param vo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPersonalOrderList", method = RequestMethod.POST)
	public Page<HotelOrder> getPersonalOrderList(@RequestBody SearchOrderVO vo, @RequestParam Long id) throws Exception {
		return hotelOrderService.getPersonalOrderList(vo, id);
	}

	/**
	 * <b>扫描中间表,执行库存更新操作</b>
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/scanTradeEnd", method = RequestMethod.POST)
//	public boolean scanTradeEnd() throws Exception {
//		return hotelOrderService.scanTradeEnd();
//	}

	/**
	 * <b>根据条件查询酒店订单</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getHotelOrderByNo", method = RequestMethod.POST)
	public HotelOrder getHotelOrderByNo(@RequestParam String orderNo) throws Exception {
		return hotelOrderService.getHotelOrderByNo(orderNo);
	}

	/**
	 * <b>修改订单信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateHotelOrder", method = RequestMethod.POST)
	public boolean updateHotelOrder(@RequestBody HotelOrder hotelOrder) throws Exception {
		return hotelOrderService.updateHotelOrder(hotelOrder);
	}
}
