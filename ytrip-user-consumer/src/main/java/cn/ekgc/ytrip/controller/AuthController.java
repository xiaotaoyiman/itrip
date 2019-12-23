package cn.ekgc.ytrip.controller;

import cn.ekgc.ytrip.base.controller.BaseController;
import cn.ekgc.ytrip.base.enums.SuccessEnum;
import cn.ekgc.ytrip.pojo.entity.User;
import cn.ekgc.ytrip.pojo.vo.ResponseResult;
import cn.ekgc.ytrip.userTransport.UserTransport;
import cn.ekgc.ytrip.util.MD5Util;
import cn.ekgc.ytrip.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>用户认证模块控制器</b>
 * @version 4.0.0 2019-12-23
 * @since 3.0.0
 */
@Api(value = "用户认证，集中完成用户注册和登录")
@RestController("authController")
@RequestMapping("/auth/api")
public class AuthController extends BaseController {
	@Autowired
	private UserTransport userTransport;

	@ApiOperation(value = "校验邮箱地址的可用性")
	@RequestMapping(value = "/ckusr", method = RequestMethod.GET)
	public ResponseResult<User> checkUserCode(String name) throws Exception {
		// 校验邮箱格式是否正确
		boolean flag = UserUtil.checkUserCode(name);
		if (flag) {
			// 验证邮箱是否已被注册
			if (userTransport.checkUserCode(name)) {
				// 邮箱是可用的
				return new ResponseResult<User>(SuccessEnum.SUCCESS_TRUE);
			}
		}
		return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "该邮箱地址不可用");
	}

	@ApiOperation(value = "保存用户注册信息")
	@RequestMapping(value = "/doregister", method = RequestMethod.POST)
	public ResponseResult<User> userRegisterByEmail(@RequestBody User user) throws Exception {
		// 验证数据的有效性
		if (user.getUserCode() != null && !"".equals(user.getUserCode().trim()) &&
				user.getUserPassword() != null && !"".equals((user.getUserPassword()))) {
			// 对用户密码进行加密处理
			user.setUserPassword(MD5Util.encrypt(user.getUserPassword()));
			// 保存用户信息
			boolean flag = userTransport.userRegisterByEmail(user);
			if (flag) {
				return new ResponseResult<User>(SuccessEnum.SUCCESS_TRUE);
			} else {
				return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "注册失败，请稍后再试");
			}
		}
		return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "请填写邮箱地址和登陆密码");
	}

	@ApiOperation(value = "激活邮箱注册用户")
	@RequestMapping(value = "/activate", method = RequestMethod.PUT)
	public ResponseResult<User>  activateUserByEmail(String user, String code) throws Exception {
		// 判断数据的有效性
		if (user != null && !"".equals(user.trim()) && code != null && !"".equals(code.trim())) {
			// 验证用户输入的邮箱地址和激活码
			boolean flag = userTransport.activateUser(user, code);
			if (flag) {
				// 说明邮箱地址正确，激活码正确
				return new ResponseResult<User>(SuccessEnum.SUCCESS_TRUE);
			}
			return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "激活失败，请重新输入");
		}
		return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "请填写邮箱地址和登陆密码");
	}
}
