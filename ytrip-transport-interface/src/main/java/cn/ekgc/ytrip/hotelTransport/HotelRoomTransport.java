package cn.ekgc.ytrip.hotelTransport;

import cn.ekgc.ytrip.pojo.entity.Image;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ytrip.pojo.vo.SearchHotelRoomVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <b>酒店房间模块传输层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@FeignClient(name = "ytrip-hotel-provider")
@RequestMapping("/hotelRoom")
public interface HotelRoomTransport {

	/**
	 * <b>查询酒店房间图片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	List<Image> getHotelRoomImg(@RequestParam Long targetId) throws Exception;

	/**
	 * <b>查询酒店房间床型列表</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelroombed", method = RequestMethod.POST)
	List<LabelDic> queryhotelroombed() throws Exception;

	/**
	 * <b>查询酒店房间列表</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hotelroombyhotel", method = RequestMethod.POST)
	List<List<HotelRoomVO>> queryhotelroombyhotel(@RequestBody SearchHotelRoomVO vo) throws Exception;
}
