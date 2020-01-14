package cn.ekgc.ytrip.service;

import cn.ekgc.ytrip.pojo.entity.AreaDic;

import java.util.List;

/**
 * <b>区域字典业务层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
public interface AreaDicService {

	/**
	 * <b>根据城市是否是国内查询热门城市</b>
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> getHotCityByType(Integer isChina) throws Exception;

	/**
	 * <b>根据城市查询商圈</b>
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	List<Object> getTradearea(Long cityId) throws Exception;
}
