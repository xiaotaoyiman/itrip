package cn.ekgc.ytrip.service.impl;

import cn.ekgc.ytrip.dao.ImageDao;
import cn.ekgc.ytrip.pojo.entity.Image;
import cn.ekgc.ytrip.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>图片子模块业务层接口实现类</b>
 * @version 4.0.0
 * @since 4.0.0
 */
@Service("imageService")
@Transactional
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageDao imageDao;

	/**
	 * <b>根据酒店id查询酒店图片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	public List<Image> getHotelImg(Long targetId) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("targetId", targetId);
		queryMap.put("type", 0);
		return imageDao.findImageListByQuery(queryMap);
	}

	/**
	 * <b>根据酒店房型id查询酒店房间图片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	public List<Image> getHotelRoomImg(Long targetId) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("targetId", targetId);
		queryMap.put("type", 1);
		return imageDao.findImageListByQuery(queryMap);
	}

	/**
	 * <b>根据targetId查询评论照片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	public List<Image> getHotelCommentImg(Long targetId) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("targetId", targetId);
		queryMap.put("type", 2);
		return imageDao.findImageListByQuery(queryMap);
	}
}
