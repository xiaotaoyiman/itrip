package cn.ekgc.ytrip.hotelTransport.impl;

import cn.ekgc.ytrip.hotelTransport.UserInfoTransport;
import cn.ekgc.ytrip.pojo.entity.UserLinkUser;
import cn.ekgc.ytrip.pojo.vo.AddUserLinkUserVO;
import cn.ekgc.ytrip.pojo.vo.ModifyUserLinkUserVO;
import cn.ekgc.ytrip.pojo.vo.SearchUserLinkUserVO;
import cn.ekgc.ytrip.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>常用联系人传输层接口实现类</b>
 * @version 4.0.0
 * @since 4.0.0
 */
@RestController("userInfoTransport")
@RequestMapping("/userInfo")
public class UserInfoTransportImpl implements UserInfoTransport {
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * <b>查询常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserLinkUser", method = RequestMethod.POST)
	public List<UserLinkUser> queryUserLinkUser(@RequestBody SearchUserLinkUserVO vo, @RequestParam Long userId) throws Exception {
		return userInfoService.queryUserLinkUser(vo, userId);
	}

	/**
	 * <b>新增常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addUserLinkUser", method = RequestMethod.POST)
	public boolean addUserLinkUser(@RequestBody AddUserLinkUserVO vo,  @RequestParam Long userId) throws Exception {
		return userInfoService.addUserLinkUser(vo, userId);
	}

	/**
	 * <b>修改常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyUserLinkUser", method = RequestMethod.POST)
	public boolean modifyUserLinkUser(@RequestBody ModifyUserLinkUserVO vo, @RequestParam Long userId) throws Exception {
		return userInfoService.modifyUserLinkUser(vo, userId);
	}

	/**
	 * <b>获得订单联系人id列表</b>
	 * @retur
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOrderLinkUser", method = RequestMethod.POST)
	public List<Long> getOrderLinkUser() throws Exception {
		return userInfoService.getOrderLinkUser();
	}

	/**
	 * <b>删除常用联系人</b>
	 * @param list
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteUserLinkUser", method = RequestMethod.POST)
	public boolean deleteUserLinkUser(@RequestParam List<Long> list, @RequestParam Long userId) throws Exception {
		return userInfoService.deleteUserLinkUser(list,userId);
	}
}
