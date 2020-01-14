package cn.ekgc.ytrip.service.impl;

import cn.ekgc.ytrip.dao.UserInfoDao;
import cn.ekgc.ytrip.pojo.entity.OrderLinkUser;
import cn.ekgc.ytrip.pojo.entity.UserLinkUser;
import cn.ekgc.ytrip.pojo.vo.AddUserLinkUserVO;
import cn.ekgc.ytrip.pojo.vo.ModifyUserLinkUserVO;
import cn.ekgc.ytrip.pojo.vo.SearchUserLinkUserVO;
import cn.ekgc.ytrip.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <b>常用联系人业务层接口实现类</b>
 * @version 4.0.0
 * @since 4.0.0
 */
@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;

	/**
	 * <b>查询常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<UserLinkUser> queryUserLinkUser(SearchUserLinkUserVO vo, Long userId) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (vo.getLinkUserName() != null && !"".equals(vo.getLinkUserName())) {
			queryMap.put("linkUserName", "%" + vo.getLinkUserName() + "%");
		}
		queryMap.put("userId", userId);
		List<UserLinkUser> list = userInfoDao.queryUserLinkUser(queryMap);
		return list;
	}

	/**
	 * <b>新增常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean addUserLinkUser(AddUserLinkUserVO vo, Long userId) throws Exception {
		UserLinkUser userLinkUser = new UserLinkUser();
		BeanUtils.copyProperties(vo, userLinkUser);
		userLinkUser.setUserId(userId.intValue());
		userLinkUser.setCreatedBy(userId);
		userLinkUser.setCreationDate(new Date());
		return userInfoDao.addUserLinkUser(userLinkUser);
	}

	/**
	 * <b>修改常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean modifyUserLinkUser(ModifyUserLinkUserVO vo, Long userId) throws Exception {
		UserLinkUser userLinkUser = new UserLinkUser();
		BeanUtils.copyProperties(vo, userLinkUser);
		userLinkUser.setUserId(userId.intValue());
		userLinkUser.setModifyDate(new Date());
		userLinkUser.setModifiedBy(userId);
		boolean flag = userInfoDao.updateUserLinkUser(userLinkUser);
		if (flag) {
			return true;
		}
		return false;
	}

	/**
	 * <b>获得订单联系人id列表</b>
	 * @retur
	 * @throws Exception
	 */
	public List<Long> getOrderLinkUser() throws Exception {
		List<OrderLinkUser> orderLinkUserList = userInfoDao.findOrderLinkUser(null);
		List<Long> list = new ArrayList<Long>();
		if (orderLinkUserList != null && orderLinkUserList.size() > 0) {
			for (OrderLinkUser orderLinkUser : orderLinkUserList) {
				list.add(orderLinkUser.getLinkUserId());
			}
			return list;
		}
		return list;
	}

	/**
	 * <b>删除常用联系人</b>
	 * @param list
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUserLinkUser(List<Long> list, Long userId) throws Exception {
		// 封装参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("list", list);
		queryMap.put("userId",userId);
		boolean flag = userInfoDao.deleteUserLinkUser(queryMap);
		if (flag) {
			return true;
		}
		return false;
	}
}
