package cn.ekgc.ytrip.service;

import cn.ekgc.ytrip.pojo.entity.Hotel;
import cn.ekgc.ytrip.pojo.vo.HotelVideoDescVO;
import cn.ekgc.ytrip.pojo.vo.SearchDetailsHotelVO;

import java.util.List;

/**
 * <b>酒店模块业务层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
public interface HotelService {

	/**
	 * <b>根据酒店id查询酒店特色、商圈、酒店名称</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	HotelVideoDescVO getVideodesc(Long id) throws Exception;

	/**
	 * <b>根据酒店id查询酒店特色和介绍</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<SearchDetailsHotelVO> queryhoteldetails(Long id) throws Exception;

	/**
	 * <b>根据酒店id查询酒店信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Hotel queryHotelData(Long id) throws Exception;
}
