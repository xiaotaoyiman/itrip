package cn.ekgc.ytrip.userTransport;

import cn.ekgc.ytrip.pojo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <b>用户模块传输层接口</b>
 * @version 4.0.0 2019-12-23
 * @since 4.0.0
 */
@FeignClient(name = "ytrip-user-provider")
@RequestMapping("/user")
public interface UserTransport {

	/**
	 * <b>根据用户提供的数据，校验其是否可用</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkUserCode", method = RequestMethod.POST)
	boolean checkUserCode(@RequestParam String userCode) throws Exception;

	/**
	 * <b>保存使用邮箱注册的用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	boolean userRegisterByEmail(@RequestBody User user) throws Exception;

	/**
	 * <b>激活注册的用户</b>
	 * @param userCode
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/active", method = RequestMethod.POST)
	boolean activateUser(@RequestParam String userCode, @RequestParam String activeCode) throws Exception;
}
