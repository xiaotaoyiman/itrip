package cn.ekgc.ytrip.hotelTransport;

import cn.ekgc.ytrip.pojo.entity.Comment;
import cn.ekgc.ytrip.pojo.entity.Image;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <b>酒店评论模块传输层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@FeignClient(name = "ytrip-hotel-provider")
@RequestMapping("/hotelComment")
public interface HotelCommentTransport {

	/**
	 * <b>根据酒店id查询酒店平均分</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelScore", method = RequestMethod.POST)
	ScoreCommentVO getHotelScore(@RequestParam Long hotelId) throws Exception;

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	Map<String, Integer> getCount(@RequestParam Long hotelId) throws Exception;

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/commentlist", method = RequestMethod.POST)
	Page<Comment> getCommentList(@RequestBody SearchCommentVO vo) throws Exception;

	/**
	 * <b>根据targetId查询评论照片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/commentimg", method = RequestMethod.POST)
	List<Image> getHotelCommentImg(@RequestParam Long targetId) throws Exception;

	/**
	 * <b>查询出游类型列表</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTravelType", method = RequestMethod.POST)
	List<LabelDic> getTravelType() throws Exception;

	/**
	 * <b>新增评论</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	boolean addComment(@RequestBody AddCommentVo vo, @RequestParam Long userId) throws Exception;
}
