package cn.ekgc.ytrip.controller;

import cn.ekgc.ytrip.base.controller.BaseController;
import cn.ekgc.ytrip.base.enums.SuccessEnum;
import cn.ekgc.ytrip.hotelTransport.UserInfoTransport;
import cn.ekgc.ytrip.pojo.entity.OrderLinkUser;
import cn.ekgc.ytrip.pojo.entity.User;
import cn.ekgc.ytrip.pojo.entity.UserLinkUser;
import cn.ekgc.ytrip.pojo.vo.*;
import cn.ekgc.ytrip.userTransport.UserTransport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>常用联系人模块控制层</b>
 * @version 4.0.0
 * @since 4.0.0
 */
@Api(value = "常用联系人模块控制层")
@RestController("userInfoController")
@RequestMapping("/biz/api/userinfo")
public class UserInfoController extends BaseController {
	@Autowired
	private UserInfoTransport userInfoTransport;
	@Autowired
	private UserTransport userTransport;


	@ApiOperation(value = "查询常用联系人")
	@RequestMapping(value = "/queryuserlinkuser", method = RequestMethod.POST)
	public ResponseResult<Object> queryUserLinkUser(@RequestBody SearchUserLinkUserVO vo) throws Exception {
		// 获得当前登录对象
		Cookie[] cookies = request.getCookies();
		String userCode = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				userCode = cookie.getValue();
			}
		}
		User user = userTransport.getUserByUserCode(userCode);
		List<UserLinkUser> list = userInfoTransport.queryUserLinkUser(vo, user.getId());
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, list);
	}

	@ApiOperation(value = "新增常用联系人")
	@RequestMapping(value = "/adduserlinkuser", method = RequestMethod.POST)
	public ResponseResult<Object> addUserLinkUser(@RequestBody AddUserLinkUserVO vo) throws Exception {
		// 获得当前登录对象
		Cookie[] cookies = request.getCookies();
		String userCode = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				userCode = cookie.getValue();
			}
		}
		User user = userTransport.getUserByUserCode(userCode);
		boolean flag = userInfoTransport.addUserLinkUser(vo, user.getId());
			if (flag) {
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "删除常用联系人")
	@RequestMapping(value = "/deluserlinkuser", method = RequestMethod.GET)
	public ResponseResult<Object> delUserLinkUser(Long[] ids) throws Exception {
		// 获得所有的订单联系人id
		List<Long> linkUserIds = userInfoTransport.getOrderLinkUser();
		// 判断要删除的常用联系人是否和订单联系人关联
		List<Long> list = new ArrayList<Long>();
		for (int i = 0; i < ids.length; i++) {
			if (!linkUserIds.contains(ids[i])) {
				list.add(ids[i]);
			}
		}
		if (list.size() <= 0) {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "不能删除");
		} else {
			// 获得当前登录对象
			Cookie[] cookies = request.getCookies();
			String userCode = "";
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					userCode = cookie.getValue();
				}
			}
			User user = userTransport.getUserByUserCode(userCode);
			boolean flag = userInfoTransport.deleteUserLinkUser(list, user.getId());
			if (flag) {
				return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
			}
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "修改常用联系人")
	@RequestMapping(value = "/modifyuserlinkuser", method = RequestMethod.POST)
	public ResponseResult<Object> modifyUserLinkUser(@RequestBody ModifyUserLinkUserVO vo) throws Exception {
		// 获得当前登录对象
		Cookie[] cookies = request.getCookies();
		String userCode = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				userCode = cookie.getValue();
			}
		}
		User user = userTransport.getUserByUserCode(userCode);
		boolean flag = userInfoTransport.modifyUserLinkUser(vo, user.getId());
		if (flag) {
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE,"修改成功");
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE,"修改失败");
	}
}
