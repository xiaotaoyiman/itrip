package cn.ekgc.ytrip.service.impl;

import cn.ekgc.ytrip.dao.HotelRoomDao;
import cn.ekgc.ytrip.pojo.entity.HotelRoom;
import cn.ekgc.ytrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ytrip.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.ytrip.service.HotelRoomService;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>酒店房间模块业务层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Service("hotelRoomService")
@Transactional
public class HotelRoomServiceImpl implements HotelRoomService {
	@Autowired
	private HotelRoomDao hotelRoomDao;

	/**
	 * <b>查询酒店房间列表</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<List<HotelRoomVO>> queryhotelroombyhotel(SearchHotelRoomVO vo) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", vo.getHotelId());
		queryMap.put("isBook", vo.getIsBook());
		queryMap.put("isCancel", vo.getIsCancel());
		queryMap.put("isTimelyResponse", vo.getIsTimelyResponse());
		queryMap.put("startDate", vo.getStartDate());
		queryMap.put("endDate", vo.getEndDate());
		queryMap.put("payType", vo.getPayType());
		queryMap.put("isHavingBreakfast", vo.getIsHavingBreakfast());
		queryMap.put("roomBedTypeId", vo.getRoomBedTypeId());
		// 查询结果
		List<HotelRoom> list = hotelRoomDao.findHotelRoomListByQuery(queryMap);
		// 将实体对象的属性复制到视图对象中
		List<List<HotelRoomVO>> voList = new ArrayList<List<HotelRoomVO>>();
		if (list != null && list.size() > 0) {
			for (HotelRoom hotelRoom : list) {
				List<HotelRoomVO> tempList = new ArrayList<HotelRoomVO>();
				HotelRoomVO hotelRoomVO = new HotelRoomVO();
				BeanUtils.copyProperties(hotelRoom, hotelRoomVO);
				tempList.add(hotelRoomVO);
				voList.add(tempList);
			}
		}
 		return voList;
	}
}
