package cn.ekgc.ytrip.controller;

import cn.ekgc.ytrip.base.controller.BaseController;
import cn.ekgc.ytrip.base.enums.SuccessEnum;
import cn.ekgc.ytrip.hotelTransport.HotelRoomTransport;
import cn.ekgc.ytrip.pojo.entity.Image;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ytrip.pojo.vo.ResponseResult;
import cn.ekgc.ytrip.pojo.vo.SearchHotelRoomVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>酒店房间模块控制器</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Api(value = "酒店房间模块控制器，酒店房间查询")
@RestController("hotelRoomController")
@RequestMapping("/biz/api/hotelroom")
public class HotelRoomController extends BaseController {
	@Autowired
	private HotelRoomTransport hotelRoomTransport;

	@ApiOperation(value = "查询酒店房间图片")
	@RequestMapping(value = "/getimg/{targetId}",method = RequestMethod.GET)
	public ResponseResult<Object> getHotelRoomImg(@PathVariable("targetId") String targetId) throws Exception {
		if (targetId != null) {
			List<Image> list = hotelRoomTransport.getHotelRoomImg(Long.valueOf(targetId));
			if (list != null && list.size() > 0) {
				return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, list);
			}
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "查询酒店房间床型列表")
	@RequestMapping(value = "/queryhotelroombed",method = RequestMethod.GET)
	public ResponseResult<Object> queryhotelroombed() throws Exception {
		List<LabelDic> list = hotelRoomTransport.queryhotelroombed();
		if (list != null && list.size() > 0) {
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, list);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}

	@ApiOperation(value = "查询酒店房间列表")
	@RequestMapping(value = "/queryhotelroombyhotel",method = RequestMethod.POST)
	public ResponseResult<Object> queryhotelroombyhotel(@RequestBody SearchHotelRoomVO vo) throws Exception {
		if (vo.getHotelId() != null && vo.getStartDate() != null && vo.getEndDate() != null) {
			List<List<HotelRoomVO>> list = hotelRoomTransport.queryhotelroombyhotel(vo);
			if (list != null && list.size() > 0) {
				return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, list);
			}
			return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
		}
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_FALSE);
	}
}
