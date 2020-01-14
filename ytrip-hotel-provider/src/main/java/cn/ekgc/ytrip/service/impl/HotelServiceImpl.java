package cn.ekgc.ytrip.service.impl;

import cn.ekgc.ytrip.dao.AreaDicDao;
import cn.ekgc.ytrip.dao.HotelDao;
import cn.ekgc.ytrip.dao.LabelDicDao;
import cn.ekgc.ytrip.pojo.entity.AreaDic;
import cn.ekgc.ytrip.pojo.entity.Hotel;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.vo.HotelVideoDescVO;
import cn.ekgc.ytrip.pojo.vo.SearchDetailsHotelVO;
import cn.ekgc.ytrip.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>酒店模块业务层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private AreaDicDao areaDicDao;
	@Autowired
	private LabelDicDao labelDicDao;

	/**
	 * <b>根据酒店id查询酒店特色、商圈、酒店名称</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HotelVideoDescVO getVideodesc(Long id) throws Exception {
		// 封装查询区域的参数
		Map<String, Object> areaDicMap = new HashMap<String, Object>();
		areaDicMap.put("id", id);
		areaDicMap.put("isActivated", 1);
		areaDicMap.put("isTradingArea", 1);
		// 查询区域商圈列表
		List<AreaDic> areaDicList = areaDicDao.getAreaDic(areaDicMap);
		List<String> tradingArea = new ArrayList<String>();
		for (AreaDic areaDic : areaDicList) {
			tradingArea.add(areaDic.getName());
		}
		// 封装查询酒店特色的参数
		List<LabelDic> labelDicList = labelDicDao.getLabelDic(id);
		List<String> feature = new ArrayList<String>();
		for (LabelDic labelDic : labelDicList) {
			feature.add(labelDic.getName());
		}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", id);
		List<Hotel> hotelList = hotelDao.findHotelListByQuery(queryMap);
		HotelVideoDescVO hotelVideoDescVO = new HotelVideoDescVO();
		hotelVideoDescVO.setHotelName(hotelList.get(0).getHotelName());
		hotelVideoDescVO.setTradingAreaNameList(tradingArea);
		hotelVideoDescVO.setHotelFeatureList(feature);
		return hotelVideoDescVO;
	}

	/**
	 * <b>根据酒店id查询酒店特色和介绍</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<SearchDetailsHotelVO> queryhoteldetails(Long id) throws Exception {
		List<SearchDetailsHotelVO> list = new ArrayList<SearchDetailsHotelVO>();
		// 封装查询酒店特色的参数
		List<LabelDic> labelDicList = labelDicDao.getLabelDic(id);
		// 封装酒店特色和详情
		for (LabelDic labelDic : labelDicList) {
			SearchDetailsHotelVO vo1 = new SearchDetailsHotelVO();
			vo1.setName(labelDic.getName());
			vo1.setDescription(labelDic.getDescription());
			list.add(vo1);
		}
		// 封装酒店介绍
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", id);
		List<Hotel> hotelList = hotelDao.findHotelListByQuery(queryMap);
		SearchDetailsHotelVO vo2 = new SearchDetailsHotelVO();
		vo2.setName("酒店介绍");
		vo2.setDescription(hotelList.get(0).getDetails());
		list.add(vo2);
		return list;
	}

	/**
	 * <b>根据酒店id查询酒店信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Hotel queryHotelData(Long id) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", id);
		List<Hotel> list = hotelDao.findHotelListByQuery(queryMap);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
