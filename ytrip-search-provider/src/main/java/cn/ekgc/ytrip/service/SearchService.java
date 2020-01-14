package cn.ekgc.ytrip.service;

import cn.ekgc.ytrip.pojo.vo.HotelVO;
import cn.ekgc.ytrip.pojo.vo.Page;
import cn.ekgc.ytrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.ytrip.pojo.vo.SearchHotelVO;

import java.util.List;

/**
 * <b>搜索模块业务层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
public interface SearchService {
	List<HotelVO> searchItripHotelListByHotCity(SearchHotCityVO vo) throws Exception;

	Page<List<HotelVO>> searchItripHotelPage(SearchHotelVO vo) throws Exception;
}
