package cn.ekgc.ytrip.dao;

import cn.ekgc.ytrip.pojo.entity.HotelTempStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>酒店库存数据持久层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Repository
public interface HotelTempStoreDao {

	/**
	 * <b>根据条件查询酒店库存信息</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer findHotelTempStoreByQuery(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>根据条件查询总库存</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer findTempStore(Map<String, Object> queryMap) throws Exception;
}
