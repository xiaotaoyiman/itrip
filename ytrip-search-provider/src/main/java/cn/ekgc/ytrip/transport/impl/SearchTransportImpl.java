package cn.ekgc.ytrip.transport.impl;

import cn.ekgc.ytrip.pojo.vo.HotelVO;
import cn.ekgc.ytrip.pojo.vo.Page;
import cn.ekgc.ytrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.ytrip.pojo.vo.SearchHotelVO;
import cn.ekgc.ytrip.searchTransport.SearchTransport;
import cn.ekgc.ytrip.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>搜索模块传输层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@RestController("searchTransport")
@RequestMapping("/search")
public class SearchTransportImpl implements SearchTransport {
	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/hotHotel", method = RequestMethod.POST)
	public List<HotelVO> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO vo) throws Exception {
		return searchService.searchItripHotelListByHotCity(vo);
	}

	@RequestMapping(value = "/hotelPage", method = RequestMethod.POST)
	public Page<List<HotelVO>> searchItripHotelPage(@RequestBody SearchHotelVO vo) throws Exception {
		return searchService.searchItripHotelPage(vo);
	}
}
