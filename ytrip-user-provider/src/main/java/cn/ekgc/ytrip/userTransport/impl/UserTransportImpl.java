package cn.ekgc.ytrip.userTransport.impl;

import cn.ekgc.ytrip.pojo.entity.User;
import cn.ekgc.ytrip.service.UserService;
import cn.ekgc.ytrip.userTransport.UserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <b>用户模块传输层接口实现类</b>
 * @version 4.0.0 2019-12-23
 * @since 4.0.0
 */
@RestController("userTransport")
@RequestMapping("/user")
public class UserTransportImpl implements UserTransport {
	@Autowired
	private UserService userService;

	/**
	 * <b>根据用户提供的数据，校验其是否可用</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkUserCode", method = RequestMethod.POST)
	public boolean checkUserCode(@RequestParam String userCode) throws Exception {
		return userService.checkUserCode(userCode);
	}

	/**
	 * <b>保存使用邮箱注册的用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public boolean userRegisterByEmail(@RequestBody User user) throws Exception {
		return userService.userRegisterByEmail(user);
	}

	/**
	 * <b>激活注册的用户</b>
	 * @param userCode
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/active", method = RequestMethod.POST)
	public boolean activateUser(@RequestParam String userCode, @RequestParam String activeCode) throws Exception {
		return userService.activateUser(userCode, activeCode);
	}
}
