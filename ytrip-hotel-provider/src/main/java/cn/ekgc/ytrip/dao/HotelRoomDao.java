package cn.ekgc.ytrip.dao;

import cn.ekgc.ytrip.pojo.entity.HotelRoom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>酒店房间模块数据持久层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Repository
public interface HotelRoomDao {

	/**
	 * <b>查询酒店房间列表</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<HotelRoom> findHotelRoomListByQuery(Map<String, Object> queryMap) throws Exception;
}
