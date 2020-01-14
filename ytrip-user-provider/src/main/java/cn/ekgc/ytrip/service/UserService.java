package cn.ekgc.ytrip.service;

import cn.ekgc.ytrip.pojo.entity.User;

/**
 * <b>用户模块业务处理层接口</b>
 * @version 4.0.0 2019-12-23
 * @since 3.0.0
 */
public interface UserService {

	/**
	 * <b>根据用户提供的数据，校验其是否可用</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	boolean checkUserCode(String userCode) throws Exception;

	/**
	 * <b>保存使用邮箱注册的用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean userRegister(User user) throws Exception;

	/**
	 * <b>激活注册用户</b>
	 * @param userCode
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	boolean activateUser(String userCode, String activeCode) throws Exception;

	/**
	 * <b>用户登录</b>
	 * @param userCode
	 * @param userPassword
	 * @return
	 * @throws Exception
	 */
	User loginUser(String userCode, String userPassword) throws Exception;

	/**
	 * <b>根据用户编码查询用户信息</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	User getUserByUserCode(String userCode) throws Exception;
}
