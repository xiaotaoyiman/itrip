package cn.ekgc.ytrip.controller;

import cn.ekgc.ytrip.base.controller.BaseController;
import cn.ekgc.ytrip.base.enums.SuccessEnum;
import cn.ekgc.ytrip.hotelTransport.HotelTransport;
import cn.ekgc.ytrip.pojo.entity.AreaDic;
import cn.ekgc.ytrip.pojo.entity.Hotel;
import cn.ekgc.ytrip.pojo.entity.Image;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.vo.HotelVideoDescVO;
import cn.ekgc.ytrip.pojo.vo.ResponseResult;
import cn.ekgc.ytrip.pojo.vo.SearchDetailsHotelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>酒店模块控制器</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Api(value = "酒店模块控制器，酒店查询、预订")
@RestController("hotelController")
@RequestMapping("/biz/api/hotel")
public class HotelController extends BaseController {
	@Autowired
	private HotelTransport hotelTransport;

	@ApiOperation(value = "查询热门城市")
	@RequestMapping(value = "/queryhotcity/{type}", method = RequestMethod.GET)
	public ResponseResult<Object> getHotCityByType(@PathVariable("type") Integer isChina) throws Exception {
		// 查询热门城市
		List<AreaDic> list = hotelTransport.getHotCityByType(isChina);
		if (list != null && list.size() > 0) {
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, list);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "查询酒店特色")
	@RequestMapping(value = "/queryhotelfeature", method = RequestMethod.GET)
	public ResponseResult<Object> getHotelFeature() throws Exception {
		// 查询酒店特色
		List<LabelDic> list = hotelTransport.getHotelFeature();
		if (list != null && list.size() > 0) {
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, list);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "查询商圈")
	@RequestMapping(value = "/querytradearea/{cityId}", method = RequestMethod.GET)
	public ResponseResult<Object> getTradearea(@PathVariable("cityId") String cityId) throws Exception {
		// 查询所在商圈
		List<Object> list = hotelTransport.getTradearea(Long.valueOf(cityId));
		if (list != null && list.size() > 0) {
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, list);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "根据酒店id查询酒店特色、商圈、酒店名称")
	@RequestMapping(value = "/getvideodesc/{hotelId}", method = RequestMethod.GET)
	public ResponseResult<Object> getVideodesc(@PathVariable("hotelId") String id) throws Exception {
		if (id != null) {
			HotelVideoDescVO hotelVideoDescVO = hotelTransport.getVideodesc(Long.valueOf(id));
			if (hotelVideoDescVO != null) {
				return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE,hotelVideoDescVO);
			}
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "根据id查询酒店图片")
	@RequestMapping(value = "/getimg/{targetId}", method = RequestMethod.GET)
	public ResponseResult<Object> getimg(@PathVariable("targetId") String targetId) throws Exception {
		if (targetId != null) {
			List<Image> list = hotelTransport.getHotelImg(Long.valueOf(targetId));
			if (list != null && list.size() > 0) {
				return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, list);
			}
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "根据酒店id查询酒店特色和介绍")
	@RequestMapping(value = "/queryhoteldetails/{id}", method = RequestMethod.GET)
	public ResponseResult<Object> queryhoteldetails(@PathVariable("id") String id) throws Exception {
		if (id != null) {
			List<SearchDetailsHotelVO> list = hotelTransport.queryhoteldetails(Long.valueOf(id));
			if (list != null && list.size() > 0) {
				return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, list);
			}
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "根据酒店id查询酒店设施")
	@RequestMapping(value = "/queryhotelfacilities/{id}", method = RequestMethod.GET)
	public ResponseResult<Object> queryhotelfacilities(@PathVariable("id") String id) throws Exception {
		if (id != null) {
			Hotel hotel = hotelTransport.queryHotelData(Long.valueOf(id));
			if (hotel != null) {
				return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE,"", hotel.getFacilities());
			}
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "根据酒店id查询酒店政策")
	@RequestMapping(value = "/queryhotelpolicy/{id}", method = RequestMethod.GET)
	public ResponseResult<Object> queryhotelpolicy(@PathVariable("id") String id) throws Exception {
		if (id != null) {
			Hotel hotel = hotelTransport.queryHotelData(Long.valueOf(id));
			if (hotel != null) {
				return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, "", hotel.getHotelPolicy());
			}
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}


}
