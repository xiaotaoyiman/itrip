package cn.ekgc.ytrip.controller;

import cn.ekgc.ytrip.base.controller.BaseController;
import cn.ekgc.ytrip.base.enums.SuccessEnum;
import cn.ekgc.ytrip.pojo.entity.User;
import cn.ekgc.ytrip.pojo.vo.ResponseResult;
import cn.ekgc.ytrip.userTransport.UserTransport;
import cn.ekgc.ytrip.util.JWTUtil;
import cn.ekgc.ytrip.util.MD5Util;
import cn.ekgc.ytrip.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;

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
	@Autowired
	private StringRedisTemplate redisTemplate;

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

	@ApiOperation(value = "保存邮箱注册用户信息")
	@RequestMapping(value = "/doregister", method = RequestMethod.POST)
	public ResponseResult<User> userRegisterByEmail(@RequestBody User user) throws Exception {
		// 验证数据的有效性
		if (user.getUserCode() != null && !"".equals(user.getUserCode().trim()) &&
				user.getUserPassword() != null && !"".equals((user.getUserPassword()))) {
			// 校验邮箱格式
			if (UserUtil.checkUserCode(user.getUserCode())) {
				// 对用户密码进行加密处理
				user.setUserPassword(MD5Util.encrypt(user.getUserPassword()));
				// 保存用户信息
				boolean flag = userTransport.userRegister(user);
				if (flag) {
					return new ResponseResult<User>(SuccessEnum.SUCCESS_TRUE);
				} else {
					return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "注册失败，请稍后再试");
				}
			}
		}
		return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "请填写邮箱地址和登陆密码");
	}

	@ApiOperation(value = "保存手机号码注册用户信息")
	@RequestMapping(value = "/registerbyphone", method = RequestMethod.POST)
	public ResponseResult<User> registerUserByCellphone(@RequestBody User user) throws Exception {
		// 验证数据的有效性
		if (user.getUserCode() != null && !"".equals(user.getUserCode().trim()) &&
				user.getUserPassword() != null && !"".equals((user.getUserPassword()))) {
			// 判断手机号码格式是否正确
			if (UserUtil.checkUserCode(user.getUserCode())) {
				// 校验用户注册的手机号码是否已被注册
				if (userTransport.checkUserCode(user.getUserCode())) {
					// 对用户信息进行加密处理
					user.setUserPassword(MD5Util.encrypt(user.getUserPassword()));
					// 保存用户注册信息
					if (userTransport.userRegister(user)) {
						return new ResponseResult<User>(SuccessEnum.SUCCESS_TRUE);
					} else {
						return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "注册失败，请稍后再试");
					}
				} else {
					return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "该手机号码已被注册，请直接登录");
				}
			} else {
				return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "请正确输入手机号码");
			}
		} else {
			return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "请填写手机号码和登陆密码");
		}
	}

	@ApiOperation(value = "激活邮箱注册用户")
	@RequestMapping(value = "/activate", method = RequestMethod.PUT)
	public ResponseResult<User>  activateUserByEmail(String user, String code) throws Exception {
		// 判断数据的有效性
		if (user != null && !"".equals(user.trim()) && code != null && !"".equals(code.trim())) {
			// 校验注册信息格式
			if (UserUtil.checkUserCode(user)) {
				// 验证用户输入的邮箱地址和激活码
				boolean flag = userTransport.activateUser(user, code);
				if (flag) {
					// 说明输入邮箱地址正确，激活码正确
					return new ResponseResult<User>(SuccessEnum.SUCCESS_TRUE);
				}
			}
			return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "激活失败，请重新输入");
		}
		return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "请填写注册信息和激活码");
	}

	@ApiOperation(value = "激活手机号码注册用户")
	@RequestMapping(value = "/validatephone", method = RequestMethod.PUT)
	public ResponseResult<User>  activateUserByPhone(String user, String code) throws Exception {
		// 判断数据的有效性
		if (user != null && !"".equals(user.trim()) && code != null && !"".equals(code.trim())) {
			// 校验注册信息格式
			if (UserUtil.checkUserCode(user)) {
				// 验证用户输入的手机号码和激活码
				boolean flag = userTransport.activateUser(user, code);
				if (flag) {
					// 说明输入手机号码正确，激活码正确
					return new ResponseResult<User>(SuccessEnum.SUCCESS_TRUE);
				}
			}
			return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "激活失败，请重新输入");
		}
		return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "请填写注册信息和激活码");
	}

	@ApiOperation(value = "用户登录")
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public ResponseResult<User> doLoginUser(String name, String password) throws Exception {
		// 判断用户输入的数据的有效性
		if (name != null && !"".equals(name.trim()) && password != null && !"".equals(password.trim())) {
			// 对密码进行加密处理
			password = MD5Util.encrypt(password);
			// 使用用户名和密码进行登录
			User user = userTransport.loginUser(name, password);
			if (user != null) {
				// 对于前后端的分离来说，登录成功之后，不在使用，或者是很少使用HTTPSession绑定用户信息
				// 对于用户的信息进行进一步Token
				String json = JWTUtil.createToken(user.getId());
				// 将生成的Token保存到Redis中
				redisTemplate.opsForValue().set("token", json);
				// 将对应的Token绑定到response的header部分
				response.setHeader("Authorization", json);
				// 登陆成功
				return new ResponseResult<User>(SuccessEnum.SUCCESS_TRUE, user);
			}
		}
		return new ResponseResult<User>(SuccessEnum.SUCCESS_FALSE, "登录失败，请确认登录信息！");
	}

	@ApiOperation(value = "用户注销")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseResult<Object> logOut() throws Exception {
		// 删除保存在Redis中的Token
		redisTemplate.delete("token");
//		Cookie[] cookies = request.getCookies();
//		for (Cookie cookie : cookies) {
//
//		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, "注销成功");
	}
}
