package cn.ekgc.ytrip.hotelTransport.impl;

import cn.ekgc.ytrip.hotelTransport.HotelCommentTransport;
import cn.ekgc.ytrip.pojo.entity.Comment;
import cn.ekgc.ytrip.pojo.entity.Image;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.vo.*;
import cn.ekgc.ytrip.service.HotelCommentService;
import cn.ekgc.ytrip.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * <b>酒店评论模块传输层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@RestController("hotelCommentTransport")
@RequestMapping("/hotelComment")
public class HotelCommentTransportImpl implements HotelCommentTransport {
	@Autowired
	private HotelCommentService hotelCommentService;
	@Autowired
	private ImageService imageService;

	/**
	 * <b>根据酒店id查询酒店平均分</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelScore", method = RequestMethod.POST)
	public ScoreCommentVO getHotelScore(@RequestParam Long hotelId) throws Exception {
		return hotelCommentService.getHotelScore(hotelId);
	}

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public Map<String, Integer> getCount(@RequestParam Long hotelId) throws Exception {
		return hotelCommentService.getCount(hotelId);
	}

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/commentlist", method = RequestMethod.POST)
	public Page<Comment> getCommentList(@RequestBody SearchCommentVO vo) throws Exception {
		return hotelCommentService.getCommentList(vo);
	}

	/**
	 * <b>根据targetId查询评论照片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/commentimg", method = RequestMethod.POST)
	public List<Image> getHotelCommentImg(@RequestParam Long targetId) throws Exception {
		return imageService.getHotelCommentImg(targetId);
	}

	/**
	 * <b>查询出游类型列表</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTravelType", method = RequestMethod.POST)
	public List<LabelDic> getTravelType() throws Exception {
		return hotelCommentService.getTravelType();
	}

	/**
	 * <b>新增评论</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public boolean addComment(@RequestBody AddCommentVo vo, @RequestParam Long userId) throws Exception {
		return hotelCommentService.addComment(vo, userId);
	}
}
