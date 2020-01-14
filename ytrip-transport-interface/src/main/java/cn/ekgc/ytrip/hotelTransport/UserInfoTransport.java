package cn.ekgc.ytrip.hotelTransport;

import cn.ekgc.ytrip.pojo.entity.UserLinkUser;
import cn.ekgc.ytrip.pojo.vo.AddUserLinkUserVO;
import cn.ekgc.ytrip.pojo.vo.ModifyUserLinkUserVO;
import cn.ekgc.ytrip.pojo.vo.SearchUserLinkUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <b>常用联系人传输层接口</b>
 * @version 4.0.0
 * @since 4.0.0
 */
@FeignClient(name = "ytrip-hotel-provider")
@RequestMapping("/userInfo")
public interface UserInfoTransport {

	/**
	 * <b>查询常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserLinkUser", method = RequestMethod.POST)
	List<UserLinkUser> queryUserLinkUser(@RequestBody SearchUserLinkUserVO vo, @RequestParam Long userId) throws Exception;

	/**
	 * <b>新增常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addUserLinkUser", method = RequestMethod.POST)
	boolean addUserLinkUser(@RequestBody AddUserLinkUserVO vo, @RequestParam Long userId) throws Exception;

	/**
	 * <b>修改常用联系人</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyUserLinkUser", method = RequestMethod.POST)
	boolean modifyUserLinkUser(@RequestBody ModifyUserLinkUserVO vo, @RequestParam Long userId) throws Exception;

	/**
	 * <b>获得订单联系人id列表</b>
	 * @retur
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOrderLinkUser", method = RequestMethod.POST)
	List<Long> getOrderLinkUser() throws Exception;

	/**
	 * <b>删除常用联系人</b>
	 * @param list
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteUserLinkUser", method = RequestMethod.POST)
	boolean deleteUserLinkUser(@RequestParam List<Long> list, @RequestParam Long userId) throws Exception;
}
