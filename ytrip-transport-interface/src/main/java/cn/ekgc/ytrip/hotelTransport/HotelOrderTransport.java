package cn.ekgc.ytrip.hotelTransport;

import cn.ekgc.ytrip.pojo.entity.HotelOrder;
import cn.ekgc.ytrip.pojo.entity.User;
import cn.ekgc.ytrip.pojo.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <b>酒店订单模块传输层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@FeignClient(name = "ytrip-hotel-provider")
@RequestMapping("/hotelOrder")
public interface HotelOrderTransport {

	/**
	 * <b>生成订单前,获取预订信息</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/preorderInfo", method = RequestMethod.POST)
	RoomStoreVO getPreorderInfo(@RequestBody ValidateRoomStoreVO vo) throws Exception;

	/**
	 * <b>生成订单前</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addHotelOrder", method = RequestMethod.POST)
	Map<String, Object> addHotelOrder(@RequestBody AddHotelOrderVO vo) throws Exception;

	/**
	 * <b>根据订单ID查看个人订单详情</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPersonalOrderInfo", method = RequestMethod.POST)
	PersonalHotelOrderVO getPersonalOrderInfo(@RequestParam Long orderId) throws Exception;

	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPersonalOrderRoomInfo", method = RequestMethod.POST)
	PersonalOrderRoomVO getPersonalOrderRoomInfo(@RequestParam Long orderId) throws Exception;

	/**
	 * <b>根据订单ID获取订单信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrderById", method = RequestMethod.POST)
	ModifyHotelOrderVO queryOrderById(@RequestParam Long orderId) throws Exception;

	/**
	 * <b>根据个人订单列表，并分页显示</b>
	 * @param vo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPersonalOrderList", method = RequestMethod.POST)
	Page<HotelOrder> getPersonalOrderList(@RequestBody SearchOrderVO vo, @RequestParam Long id) throws Exception;

	/**
	 * <b>扫描中间表,执行库存更新操作</b>
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/scanTradeEnd", method = RequestMethod.POST)
//	boolean scanTradeEnd() throws Exception;

	/**
	 * <b>根据条件查询酒店订单</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getHotelOrderByNo", method = RequestMethod.POST)
	HotelOrder getHotelOrderByNo(@RequestParam String orderNo) throws Exception;

	/**
	 * <b>修改订单信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateHotelOrder", method = RequestMethod.POST)
	boolean updateHotelOrder(@RequestBody HotelOrder hotelOrder) throws Exception;
}
