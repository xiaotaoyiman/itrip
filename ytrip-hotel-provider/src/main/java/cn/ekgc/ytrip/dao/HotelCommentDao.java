package cn.ekgc.ytrip.dao;

import cn.ekgc.ytrip.pojo.entity.Comment;
import cn.ekgc.ytrip.pojo.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>酒店评论数据持久层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Repository
public interface HotelCommentDao {

	/**
	 * <b>据酒店id查询酒店评论信息</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<Comment> findCommentByQuery(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>据酒店id查询酒店平均分</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<Score> findCommentScoreByQuery(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>新增评论</b>
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	boolean saveComment(Comment comment) throws Exception;
}
