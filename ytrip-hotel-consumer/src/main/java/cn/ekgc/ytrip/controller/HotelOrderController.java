package cn.ekgc.ytrip.controller;

import cn.ekgc.ytrip.base.controller.BaseController;
import cn.ekgc.ytrip.base.enums.SuccessEnum;
import cn.ekgc.ytrip.hotelTransport.HotelOrderTransport;
import cn.ekgc.ytrip.pojo.entity.HotelOrder;
import cn.ekgc.ytrip.pojo.entity.User;
import cn.ekgc.ytrip.pojo.vo.*;
import cn.ekgc.ytrip.userTransport.UserTransport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.Map;

/**
 * <b>酒店订单模块控制器</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Api(value = "酒店订单模块控制器，酒店订单")
@RestController("hotelOrderController")
@RequestMapping("/biz/api/hotelorder")
public class HotelOrderController extends BaseController {
	@Autowired
	private HotelOrderTransport hotelOrderTransport;
	@Autowired
	private UserTransport userTransport;

	@ApiOperation(value = "生成订单前,获取预订信息")
	@RequestMapping(value = "/getpreorderinfo", method = RequestMethod.POST)
	public ResponseResult<Object> getPreOrderInfo(@RequestBody ValidateRoomStoreVO vo) throws Exception {
		// 获得当前Token信息
		// String tokenString = request.getHeader("Authorization");
		// 对于给定的Token进行校验和解密
		// Long userId = JWTUtil.decryptToken(tokenString);
		// 判断当前用户是否失效
		//if (userId > 0) {
			if (vo.getHotelId() != null) {
				if (vo.getRoomId() != null) {
					RoomStoreVO roomStoreVO = hotelOrderTransport.getPreorderInfo(vo);
					return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, roomStoreVO);
				} else {
					return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE, "roomId不能为空！");
				}
			} else {
				return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE, "hotelId不能为空！");
			}
		//} else {
		//	return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE, "Token失效，请重新登录！");
		//}
	}

	@ApiOperation(value = "生成订单")
	@RequestMapping(value = "/addhotelorder", method = RequestMethod.POST)
	public ResponseResult<Object> addHotelOrder(@RequestBody AddHotelOrderVO vo) throws Exception {
		// 判断用户预定的酒店房间库存是否满足
		ValidateRoomStoreVO validateRoomStoreVO = new ValidateRoomStoreVO();
		validateRoomStoreVO.setHotelId(vo.getHotelId());
		validateRoomStoreVO.setRoomId(vo.getRoomId());
		validateRoomStoreVO.setCount(vo.getCount());
		validateRoomStoreVO.setCheckInDate(vo.getCheckInDate());
		validateRoomStoreVO.setCheckOutDate(vo.getCheckOutDate());
		RoomStoreVO roomStoreVO = hotelOrderTransport.getPreorderInfo(validateRoomStoreVO);
		if (roomStoreVO.getStore() != null && roomStoreVO.getStore() >= vo.getCount()) {
			// 表示酒店库存足够
			// 得到当前登录用户信息
			Cookie[] cookies = request.getCookies();
			String userCode = "";
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					userCode = cookie.getValue();
				}
			}
			// 获得当前登录用户主键
			User user = userTransport.getUserByUserCode(userCode);
			vo.setUserId(user.getId());
			Map<String, Object> map =hotelOrderTransport.addHotelOrder(vo);
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, map);
		} else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE,"库存不足");
		}
	}

	@ApiOperation(value = "根据订单ID查看个人订单详情")
	@RequestMapping(value = "/getpersonalorderinfo/{orderId}", method = RequestMethod.GET)
	public ResponseResult<Object> getPersonalOrderInfo(@PathVariable("orderId") Long orderId) throws Exception {
		if (orderId != null) {
			// 查询用户订单详情
			PersonalHotelOrderVO personalHotelOrderVO = hotelOrderTransport.getPersonalOrderInfo(orderId);
			if (personalHotelOrderVO != null) {
				return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, personalHotelOrderVO);
			} else {
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "订单不存在");
			}
		}

		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "根据订单ID查看个人订单详情-房型相关信息")
	@RequestMapping(value = "/getpersonalorderroominfo/{orderId}", method = RequestMethod.GET)
	public ResponseResult<Object> getPersonalOrderRoomInfo(@PathVariable("orderId") Long orderId) throws Exception {
		if (orderId != null) {
			PersonalOrderRoomVO vo = hotelOrderTransport.getPersonalOrderRoomInfo(orderId);
			if (vo != null) {
				return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, vo);
			}
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "没有相关订单房型信息");
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请传递参数：orderId");
	}

	@ApiOperation(value = "根据订单ID获取订单信息")
	@RequestMapping(value = "/queryOrderById/{orderId}", method = RequestMethod.GET)
	public ResponseResult<Object> queryOrderById(@PathVariable("orderId") Long orderId) throws Exception {
		if (orderId != null) {
			ModifyHotelOrderVO vo = hotelOrderTransport.queryOrderById(orderId);
			if (vo != null) {
				return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, vo);
			}
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "没有相关订单信息");
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请传递参数：orderId");
	}

	@ApiOperation(value = "根据个人订单列表，并分页显示")
	@RequestMapping(value = "/getpersonalorderlist", method = RequestMethod.POST)
	public ResponseResult<Object> getPersonalOrderList(@RequestBody SearchOrderVO vo) throws Exception {
		// 得到当前登录用户信息
		Cookie[] cookies = request.getCookies();
		String userCode = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				userCode = cookie.getValue();
			}
		}
		// 获得当前登录用户
		User user = userTransport.getUserByUserCode(userCode);
		Page<HotelOrder> page = hotelOrderTransport.getPersonalOrderList(vo, user.getId());
		if (page != null) {
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, page);
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
	}

//	@ApiOperation(value = "扫描中间表,执行库存更新操作")
//	@RequestMapping(value = "/scanTradeEnd", method = RequestMethod.GET)
//	public ResponseResult<Object> scanTradeEnd() throws Exception {
//		boolean flag = hotelOrderTransport.scanTradeEnd();
//		if (flag) {
//			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
//		}
//		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
//	}
}
