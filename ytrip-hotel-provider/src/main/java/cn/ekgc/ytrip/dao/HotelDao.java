package cn.ekgc.ytrip.dao;

import cn.ekgc.ytrip.pojo.entity.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>酒店模块数据持久层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Repository
public interface HotelDao {

	/**
	 * <b>根据酒店id查询酒店信息</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<Hotel> findHotelListByQuery(Map<String, Object> queryMap) throws Exception;
}
