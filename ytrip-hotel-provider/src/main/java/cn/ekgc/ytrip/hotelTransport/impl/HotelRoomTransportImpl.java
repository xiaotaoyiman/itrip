package cn.ekgc.ytrip.hotelTransport.impl;

import cn.ekgc.ytrip.hotelTransport.HotelRoomTransport;
import cn.ekgc.ytrip.pojo.entity.Image;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ytrip.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.ytrip.service.HotelRoomService;
import cn.ekgc.ytrip.service.ImageService;
import cn.ekgc.ytrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>酒店房间模块传输层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@RestController("hotelRoomTransport")
@RequestMapping("/hotelRoom")
public class HotelRoomTransportImpl implements HotelRoomTransport {
	@Autowired
	private HotelRoomService hotelRoomService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private LabelDicService labelDicService;

	/**
	 * <b>查询酒店房间图片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public List<Image> getHotelRoomImg(@RequestParam Long targetId) throws Exception {
		return imageService.getHotelRoomImg(targetId);
	}

	/**
	 * <b>查询酒店房间床型列表</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelroombed", method = RequestMethod.POST)
	public List<LabelDic> queryhotelroombed() throws Exception {
		return labelDicService.queryhotelroombed();
	}

	/**
	 * <b>查询酒店房间列表</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelroombyhotel", method = RequestMethod.POST)
	public List<List<HotelRoomVO>> queryhotelroombyhotel(@RequestBody SearchHotelRoomVO vo) throws Exception {
		return hotelRoomService.queryhotelroombyhotel(vo);
	}
}
