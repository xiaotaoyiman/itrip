package cn.ekgc.ytrip.dao;

import cn.ekgc.ytrip.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

	/**
	 * <b>根据用户提供的数据，校验其是否可用</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<User> findUserListByQuery(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>保存注册用户信息</b>
	 * @param user
	 * @throws Exception
	 */
	void saveUser(User user) throws Exception;

	/**
	 * <b>激活注册用户</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean updateUser(User user) throws Exception;
}
