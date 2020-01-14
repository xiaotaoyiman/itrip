package cn.ekgc.ytrip.dao;

import cn.ekgc.ytrip.pojo.entity.OrderLinkUser;
import cn.ekgc.ytrip.pojo.entity.UserLinkUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>常用联系人数据持久层接口</b>
 * @version 4.0.0
 * @since 4.0.0
 */
@Repository
public interface UserInfoDao {

	/**
	 * <b>查询常用联系人</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<UserLinkUser> queryUserLinkUser(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>新增常用联系人</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	boolean addUserLinkUser(UserLinkUser userLinkUser) throws Exception;

	/**
	 * <b>修改常用联系人</b>
	 * @param userLinkUser
	 * @return
	 * @throws Exception
	 */
	boolean updateUserLinkUser(UserLinkUser userLinkUser) throws Exception;

	/**
	 * <b>获得订单联系人列表</b>
	 * @param queryMap
	 * @retur
	 * @throws Exception
	 */
	List<OrderLinkUser> findOrderLinkUser(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>删除常用联系人</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	boolean deleteUserLinkUser(Map<String, Object> queryMap) throws Exception;
}
