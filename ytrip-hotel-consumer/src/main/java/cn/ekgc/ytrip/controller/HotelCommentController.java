package cn.ekgc.ytrip.controller;

import cn.ekgc.ytrip.base.controller.BaseController;
import cn.ekgc.ytrip.base.enums.SuccessEnum;
import cn.ekgc.ytrip.hotelTransport.HotelCommentTransport;
import cn.ekgc.ytrip.hotelTransport.HotelTransport;
import cn.ekgc.ytrip.pojo.entity.*;
import cn.ekgc.ytrip.pojo.vo.*;
import cn.ekgc.ytrip.userTransport.UserTransport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Map;

/**
 * <b>酒店评论模块控制器</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Api(value = "酒店评论模块控制器，酒店评论查询")
@RestController("hotelCommentController")
@RequestMapping("/biz/api/comment")
public class HotelCommentController extends BaseController {
	@Autowired
	private HotelCommentTransport hotelCommentTransport;
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private UserTransport userTransport;

	@ApiOperation(value = "根据酒店id查询酒店平均分")
	@RequestMapping(value = "/gethotelscore/{hotelId}", method = RequestMethod.GET)
	public ResponseResult<Object> getHotelScore(@PathVariable("hotelId") String hotelId) throws Exception {
		if (hotelId != null) {
			ScoreCommentVO scoreCommentVO = hotelCommentTransport.getHotelScore(Long.valueOf(hotelId));
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, scoreCommentVO);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "根据酒店id查询各类评论数量")
	@RequestMapping(value = "/getcount/{hotelId}", method = RequestMethod.GET)
	public ResponseResult<Object> getCount(@PathVariable("hotelId") String hotelId) throws Exception {
		if (hotelId != null) {
			Map<String, Integer> countMap = hotelCommentTransport.getCount(Long.valueOf(hotelId));
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, countMap);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "根据评论类型查询评论列表，并分页显示")
	@RequestMapping(value = "/getcommentlist", method = RequestMethod.POST)
	public ResponseResult<Object> getCommentList(@RequestBody SearchCommentVO vo) throws Exception {
		if (vo != null) {
			Page<Comment> page = hotelCommentTransport.getCommentList(vo);
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, page);
 		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "根据targetId查询评论照片")
	@RequestMapping(value = "/getimg/{targetId}",method = RequestMethod.GET)
	public ResponseResult<Object> getHotelRoomImg(@PathVariable("targetId") String targetId) throws Exception {
		if (targetId != null) {
			List<Image> list = hotelCommentTransport.getHotelCommentImg(Long.valueOf(targetId));
			if (list != null && list.size() > 0) {
				return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, list);
			}
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "获取酒店相关信息（酒店名称、酒店星级）")
	@RequestMapping(value = "/gethoteldesc/{hotelId}",method = RequestMethod.GET)
	public ResponseResult<Object> getHotelDesc(@PathVariable("hotelId") Long id) throws Exception {
		Hotel hotel = hotelTransport.queryHotelData(id);
		if (hotel != null) {
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, hotel);
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "查询出游类型列表")
	@RequestMapping(value = "/gettraveltype",method = RequestMethod.GET)
	public ResponseResult<Object> getTravelType() throws Exception {
		List<LabelDic> list = hotelCommentTransport.getTravelType();
		if (list != null && list.size() > 0) {
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, list);
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "新增评论")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseResult<Object> addComment(@RequestBody AddCommentVo vo) throws Exception {
		// 获得当前登录用户
		Cookie[] cookies = request.getCookies();
		String userCode = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				userCode = cookie.getValue();
			}
		}
		// 获得当前登录用户
		User user = userTransport.getUserByUserCode(userCode);
		if (vo.getOrderId() != null) {
			boolean flag = hotelCommentTransport.addComment(vo, user.getId());
			if (flag) {
				return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
			} else {
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "新增评论失败");
			}
		} else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "订单不能为空");
		}
	}
}
