package cn.ekgc.ytrip.dao;

import cn.ekgc.ytrip.pojo.entity.AreaDic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>区域字典数据持久层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Repository
public interface AreaDicDao {

	/**
	 * <b>根据城市是否是国内查询热门城市</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> getHotCityByQuery(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>根据城市查询商圈</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<Object> findTradeareaByQuery(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>根据条件查询区域</b>
	 * @param areaDicMap
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> getAreaDic(Map<String, Object> areaDicMap) throws Exception;
}
