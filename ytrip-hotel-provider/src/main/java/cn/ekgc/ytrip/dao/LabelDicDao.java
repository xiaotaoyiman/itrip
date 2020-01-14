package cn.ekgc.ytrip.dao;

import cn.ekgc.ytrip.pojo.entity.LabelDic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>通用字典数据持久层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Repository
public interface LabelDicDao {

	/**
	 * <b>查询酒店特色列表</b>
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> findHotelFeatureByQuery(Map<String, Object> qureyMap) throws Exception;

	/**
	 * <b>查询酒店特色列表</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> getLabelDic(Long id) throws Exception;
}
