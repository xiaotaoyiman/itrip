package cn.ekgc.ytrip.hotelTransport;

import cn.ekgc.ytrip.pojo.entity.AreaDic;
import cn.ekgc.ytrip.pojo.entity.Hotel;
import cn.ekgc.ytrip.pojo.entity.Image;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.vo.HotelVideoDescVO;
import cn.ekgc.ytrip.pojo.vo.SearchDetailsHotelVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <b>酒店模块传输层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@FeignClient(name = "ytrip-hotel-provider")
@RequestMapping("/hotel")
public interface HotelTransport {

	/**
	 * <b>根据城市是否是国内查询热门城市</b>
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotCity", method = RequestMethod.POST)
	List<AreaDic> getHotCityByType(@RequestParam Integer isChina) throws Exception;

	/**
	 * <b>查询酒店特色列表</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/faeture", method = RequestMethod.POST)
	List<LabelDic> getHotelFeature() throws Exception;

	/**
	 * <b>根据城市查询商圈</b>
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/area", method = RequestMethod.POST)
	List<Object> getTradearea(@RequestParam Long cityId) throws Exception;

	/**
	 * <b>根据酒店id查询酒店图片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelImg", method = RequestMethod.POST)
	List<Image> getHotelImg(@RequestParam Long targetId) throws Exception;

	/**
	 * <b>根据酒店id查询酒店特色、商圈、酒店名称</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/videodesc", method = RequestMethod.POST)
	HotelVideoDescVO getVideodesc(@RequestParam Long id) throws Exception;

	/**
	 * <b>根据酒店id查询酒店特色和介绍</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hoteldetails", method = RequestMethod.POST)
	List<SearchDetailsHotelVO> queryhoteldetails(@RequestParam Long id) throws Exception;

	/**
	 * <b>根据酒店id查询酒店信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelfacilities", method = RequestMethod.POST)
	Hotel queryHotelData(@RequestParam Long id) throws Exception;
}
