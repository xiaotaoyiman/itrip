package cn.ekgc.ytrip.hotelTransport.impl;

import cn.ekgc.ytrip.hotelTransport.HotelTransport;
import cn.ekgc.ytrip.pojo.entity.AreaDic;
import cn.ekgc.ytrip.pojo.entity.Hotel;
import cn.ekgc.ytrip.pojo.entity.Image;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.vo.HotelVideoDescVO;
import cn.ekgc.ytrip.pojo.vo.SearchDetailsHotelVO;
import cn.ekgc.ytrip.service.AreaDicService;
import cn.ekgc.ytrip.service.HotelService;
import cn.ekgc.ytrip.service.ImageService;
import cn.ekgc.ytrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>酒店模块传输层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@RestController("hotelTransport")
@RequestMapping("/hotel")
public class HotelTransportImpl implements HotelTransport {
	@Autowired
	private AreaDicService areaDicService;
	@Autowired
	private LabelDicService labelDicService;
	@Autowired
	private HotelService hotelService;
	@Autowired
	private ImageService imageService;


	/**
	 * <b>根据城市是否是国内查询热门城市</b>
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotCity", method = RequestMethod.POST)
	public List<AreaDic> getHotCityByType(@RequestParam Integer isChina) throws Exception {
		return areaDicService.getHotCityByType(isChina);
	}

	/**
	 * <b>查询酒店特色列表</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/faeture", method = RequestMethod.POST)
	public List<LabelDic> getHotelFeature() throws Exception {
		return labelDicService.getHotelFeature();
	}

	/**
	 * <b>根据城市查询商圈</b>
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/area", method = RequestMethod.POST)
	public List<Object> getTradearea(@RequestParam Long cityId) throws Exception {
		return areaDicService.getTradearea(cityId);
	}

	/**
	 * <b>根据酒店id查询酒店图片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelImg", method = RequestMethod.POST)
	public List<Image> getHotelImg(@RequestParam Long targetId) throws Exception {
		return imageService.getHotelImg(targetId);
	}

	/**
	 * <b>根据酒店id查询酒店特色、商圈、酒店名称</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/videodesc", method = RequestMethod.POST)
	public HotelVideoDescVO getVideodesc(@RequestParam Long id) throws Exception {
		return hotelService.getVideodesc(id);
	}

	/**
	 * <b>根据酒店id查询酒店特色和介绍</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hoteldetails", method = RequestMethod.POST)
	public List<SearchDetailsHotelVO> queryhoteldetails(@RequestParam Long id) throws Exception {
		return hotelService.queryhoteldetails(id);
	}

	/**
	 * <b>根据酒店id查询酒店信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelfacilities", method = RequestMethod.POST)
	public Hotel queryHotelData(@RequestParam Long id) throws Exception {
		return hotelService.queryHotelData(id);
	}
}
