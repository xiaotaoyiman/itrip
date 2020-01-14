package cn.ekgc.ytrip.service.impl;

import cn.ekgc.ytrip.dao.HotelCommentDao;
import cn.ekgc.ytrip.dao.HotelOrderDao;
import cn.ekgc.ytrip.dao.LabelDicDao;
import cn.ekgc.ytrip.pojo.entity.Comment;
import cn.ekgc.ytrip.pojo.entity.HotelOrder;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.entity.Score;
import cn.ekgc.ytrip.pojo.vo.*;
import cn.ekgc.ytrip.service.HotelCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>酒店评论业务层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Service("hotelCommentService")
@Transactional
public class HotelCommentServiceImpl implements HotelCommentService {
	@Autowired
	private HotelCommentDao hotelCommentDao;
	@Autowired
	private LabelDicDao labelDicDao;
	@Autowired
	private HotelOrderDao hotelOrderDao;

	/**
	 * <b>据酒店id查询酒店平均分</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	public ScoreCommentVO getHotelScore(Long hotelId) throws Exception {
		//封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", hotelId);
		queryMap.put("productType", 1);
		// 查询结果
		List<Score> commentList = hotelCommentDao.findCommentScoreByQuery(queryMap);
		if (commentList != null && commentList.size() > 0) {
			ScoreCommentVO scoreCommentVO = new ScoreCommentVO();
			scoreCommentVO.setAvgFacilitiesScore(commentList.get(0).getAvgFacilitiesScore());
			scoreCommentVO.setAvgHygieneScore(commentList.get(0).getAvgHygieneScore());
			scoreCommentVO.setAvgPositionScore(commentList.get(0).getAvgPositionScore());
			scoreCommentVO.setAvgScore(commentList.get(0).getAvgScore());
			scoreCommentVO.setAvgServiceScore(commentList.get(0).getAvgServiceScore());
			return scoreCommentVO;
		}
		return null;
	}

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Integer> getCount(Long hotelId) throws Exception {
		// 创建返回对象集合
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		//封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", hotelId);
		queryMap.put("productType", 1);
		// 查询结果
		List<Comment> commentList = hotelCommentDao.findCommentByQuery(queryMap);
		// 总评论数量
		countMap.put("allcomment", commentList.size());
		// 有待改善评论数量
		queryMap.put("isOk", 0);
		commentList = hotelCommentDao.findCommentByQuery(queryMap);
		countMap.put("improve", commentList.size());
		// 值得推荐的评论的数量
		queryMap.put("isOk", 1);
		commentList = hotelCommentDao.findCommentByQuery(queryMap);
		countMap.put("isok", commentList.size());
		// 查询有图片的评论数量
		queryMap.remove("isOk");
		queryMap.put("isHavingImg", 1);
		commentList = hotelCommentDao.findCommentByQuery(queryMap);
		countMap.put("havingimg", commentList.size());
		return countMap;
	}

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Page<Comment> getCommentList(SearchCommentVO vo) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", vo.getHotelId());
		if (vo.getIsHavingImg() != -1) {
			queryMap.put("isHavingImg", vo.getIsHavingImg());
		}
		if (vo.getIsOk() != -1) {
			queryMap.put("isOk", vo.getIsOk());
		}
		List<Comment> list = hotelCommentDao.findCommentByQuery(queryMap);
		// 查询总条数
		Integer total = list.size();
		Page<Comment> page = new Page<Comment>(vo.getPageNo(), vo.getPageSize(), total);
		queryMap.put("beginPos", page.getBeginPos());
		queryMap.put("pageSize", page.getPageSize());
		List<Comment> commentList = hotelCommentDao.findCommentByQuery(queryMap);
		page.setRows(commentList);
		return page;
	}

	/**
	 * <b>查询出游类型列表</b>
	 * @return
	 * @throws Exception
	 */
	public List<LabelDic> getTravelType() throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("parentId", 107);
		List<LabelDic> list = labelDicDao.findHotelFeatureByQuery(queryMap);
		return list;
	}

	/**
	 * <b>新增评论</b>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean addComment(AddCommentVo vo, Long userId) throws Exception {
		// 封装订单对象
		Comment comment = new Comment();
		BeanUtils.copyProperties(vo,comment);
		comment.setUserId(userId);
		comment.setCreatedBy(userId);
		comment.setCreationDate(new Date());
		Integer score = (vo.getFacilitiesScore() + vo.getHygieneScore() + vo.getPositionScore() + vo.getServiceScore()) / 4;
		comment.setScore(score);
		// 保存评论信息
		boolean flag = hotelCommentDao.saveComment(comment);
		if (flag) {
			// 评论信息保存成功，修改订单状态
			HotelOrder hotelOrder = new HotelOrder();
			hotelOrder.setModifyDate(new Date());
			hotelOrder.setId(vo.getOrderId());
			hotelOrder.setModifiedBy(userId);
			hotelOrder.setOrderStatus(4);
			flag = hotelOrderDao.updateHotelOrder(hotelOrder);
			return flag;
		}
		return flag;
	}
}
