package cn.ekgc.ytrip.service.impl;

import cn.ekgc.ytrip.dao.UserDao;
import cn.ekgc.ytrip.pojo.entity.User;
import cn.ekgc.ytrip.service.UserService;
import cn.ekgc.ytrip.util.ConstantUtil;
import cn.ekgc.ytrip.util.ProduceActiveCode;
import cn.ekgc.ytrip.util.communication.email.EmailUtil;
import cn.ekgc.ytrip.util.communication.phone.NoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <b>用户模块业务处理层接口实现类</b>
 * @version 4.0.0 2019-12-23
 * @since 3.0.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmailUtil emailUtil;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private NoteUtil noteUtil;

	/**
	 * <b>根据用户提供的数据，校验其是否可用</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public boolean checkUserCode(String userCode) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userCode", userCode);
		// 查询结果
		List<User> list = userDao.findUserListByQuery(queryMap);
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * <b>保存注册的用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean userRegister(User user) throws Exception {
		try {
			// 保存用户信息
			userDao.saveUser(user);
			// 生成激活码
			String activeCode = ProduceActiveCode.getActiveCode();
			// 发送邮件或短信
			if (user.getUserCode().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
				// 发送邮件
				emailUtil.sendEmail(user.getUserCode(), activeCode);
			} else {
				// 发送短信
				noteUtil.sendNote(user.getUserCode(), activeCode);
			}
			// 将激活码保存到Redis中
			redisTemplate.opsForValue().set(user.getUserCode(),activeCode);
			// 对于该存入redis的key设置有效时间
			redisTemplate.expire(user.getUserCode(), ConstantUtil.ACTIVE_CODE_TIMEOUT * 60, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * <b>激活注册用户</b>
	 * @param userCode
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public boolean activateUser(String userCode, String activeCode) throws Exception {
		// 从Redis中获取存取的验证码
		String redisCode = (String) redisTemplate.opsForValue().get(userCode);
		if (redisCode != null) {
			// 验证用户输入的激活码是否正确
			if (redisCode.equals(activeCode)) {
				// 说明用户输入的激活码有效，激活用户账号
				User user = new User();
				user.setUserCode(userCode);
				user.setActivated(1);
				if (userDao.updateUser(user)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <b>用户登录</b>
	 * @param userCode
	 * @param userPassword
	 * @return
	 * @throws Exception
	 */
	public User loginUser(String userCode, String userPassword) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userCode", userCode);
		queryMap.put("userPassword", userPassword);
		// 查询结果
		List<User> list = userDao.findUserListByQuery(queryMap);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * <b>根据用户编码查询用户信息</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public User getUserByUserCode(String userCode) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userCode", userCode);
		// 查询结果
		List<User> list = userDao.findUserListByQuery(queryMap);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
