package cn.ekgc.ytrip.service;

import cn.ekgc.ytrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ytrip.pojo.vo.SearchHotelRoomVO;

import java.util.List;

/**
 * <b>酒店房间模块业务层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
public interface HotelRoomService {

	/**
	 * <b>查询酒店房间列表</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List<List<HotelRoomVO>> queryhotelroombyhotel(SearchHotelRoomVO vo) throws Exception;
}
