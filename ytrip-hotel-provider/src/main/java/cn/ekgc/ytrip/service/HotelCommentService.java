package cn.ekgc.ytrip.service;

import cn.ekgc.ytrip.pojo.entity.Comment;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.vo.*;

import java.util.List;
import java.util.Map;

/**
 * <b>酒店评论业务层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
public interface HotelCommentService {

	/**
	 * <b>根据酒店id查询酒店平均分</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	ScoreCommentVO getHotelScore(Long hotelId) throws Exception;

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	Map<String, Integer> getCount(Long hotelId) throws Exception;

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	Page<Comment> getCommentList(SearchCommentVO vo) throws Exception;

	/**
	 * <b>查询出游类型列表</b>
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> getTravelType() throws Exception;

	/**
	 * <b>新增评论</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean addComment(AddCommentVo vo, Long userId) throws Exception;
}
