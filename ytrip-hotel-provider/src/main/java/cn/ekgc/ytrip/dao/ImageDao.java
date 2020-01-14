package cn.ekgc.ytrip.dao;

import cn.ekgc.ytrip.pojo.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>图片子模块数据持久层接口</b>
 * @version 4.0.0
 * @since 4.0.0
 */
@Repository
public interface ImageDao {

	/**
	 * <b>根据条件查询图片列表</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<Image> findImageListByQuery(Map<String, Object> queryMap) throws Exception;
}
