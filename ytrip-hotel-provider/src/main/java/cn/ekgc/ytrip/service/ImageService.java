package cn.ekgc.ytrip.service;

import cn.ekgc.ytrip.pojo.entity.Image;

import java.util.List;

/**
 * <b>图片子模块业务层接口</b>
 * @version 4.0.0
 * @since 4.0.0
 */
public interface ImageService {

	/**
	 * <b>根据酒店id查询酒店图片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	List<Image> getHotelImg(Long targetId) throws Exception;

	/**
	 * <b>根据酒店房型id查询酒店房间图片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	List<Image> getHotelRoomImg(Long targetId) throws Exception;

	/**
	 * <b>根据targetId查询评论照片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	List<Image> getHotelCommentImg(Long targetId) throws Exception;
}
