package cn.ekgc.ytrip.controller;

import cn.ekgc.ytrip.base.enums.SuccessEnum;
import cn.ekgc.ytrip.pojo.vo.*;
import cn.ekgc.ytrip.searchTransport.SearchTransport;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>搜索模块控制器</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Api(value = "搜索模块控制器")
@RestController("searchController")
@RequestMapping("/search/api/hotellist")
public class SearchController {
	@Autowired
	private SearchTransport searchTransport;

	@RequestMapping(value = "/searchItripHotelListByHotCity", method = RequestMethod.POST)
	public ResponseResult<Object> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO vo) throws Exception {
		if (vo != null && vo.getCityId() != null) {
			List<HotelVO> list = searchTransport.searchItripHotelListByHotCity(vo);
			if (list != null && list.size() > 0) {
				return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, list);
			}
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
	}

	@RequestMapping(value = "/searchItripHotelPage", method = RequestMethod.POST)
	public ResponseResult<Object> searchItripHotelPage(@RequestBody SearchHotelVO vo) throws Exception {
		if (vo != null && vo.getDestination() != null) {
			Page<List<HotelVO>> page = searchTransport.searchItripHotelPage(vo);
			if (page != null) {
				return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, page);
			}
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
	}

}
