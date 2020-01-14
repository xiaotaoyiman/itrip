package cn.ekgc.ytrip.searchTransport;

import cn.ekgc.ytrip.pojo.vo.HotelVO;
import cn.ekgc.ytrip.pojo.vo.Page;
import cn.ekgc.ytrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.ytrip.pojo.vo.SearchHotelVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <b>搜索模块传输层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@FeignClient(name = "ytrip-search-provider")
@RequestMapping("/search")
public interface SearchTransport {

	@RequestMapping(value = "/hotHotel", method = RequestMethod.POST)
	List<HotelVO> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO vo) throws Exception;

	@RequestMapping(value = "/hotelPage", method = RequestMethod.POST)
	Page<List<HotelVO>> searchItripHotelPage(@RequestBody SearchHotelVO vo) throws Exception;

}
