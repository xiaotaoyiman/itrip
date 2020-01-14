package cn.ekgc.ytrip.service;

import cn.ekgc.ytrip.pojo.entity.UserLinkUser;
import cn.ekgc.ytrip.pojo.vo.AddUserLinkUserVO;
import cn.ekgc.ytrip.pojo.vo.ModifyUserLinkUserVO;
import cn.ekgc.ytrip.pojo.vo.SearchUserLinkUserVO;

import java.util.List;

/**
 * <b>常用联系人业务层接口</b>
 * @version 4.0.0
 * @since 4.0.0
 */
public interface UserInfoService {

	/**
	 * <b>查询常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<UserLinkUser> queryUserLinkUser(SearchUserLinkUserVO vo, Long userId) throws Exception;

	/**
	 * <b>新增常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean addUserLinkUser(AddUserLinkUserVO vo, Long userId) throws Exception;

	/**
	 * <b>修改常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean modifyUserLinkUser(ModifyUserLinkUserVO vo, Long userId) throws Exception;

	/**
	 * <b>获得订单联系人id列表</b>
	 * @retur
	 * @throws Exception
	 */
	List<Long> getOrderLinkUser() throws Exception;

	/**
	 * <b>删除常用联系人</b>
	 * @param list
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean deleteUserLinkUser(List<Long> list, Long userId) throws Exception;
}
