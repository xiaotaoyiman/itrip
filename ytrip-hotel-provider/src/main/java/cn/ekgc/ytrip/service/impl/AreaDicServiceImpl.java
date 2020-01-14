package cn.ekgc.ytrip.service.impl;

import cn.ekgc.ytrip.dao.AreaDicDao;
import cn.ekgc.ytrip.pojo.entity.AreaDic;
import cn.ekgc.ytrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>区域字典业务层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Service("areaDicService")
@Transactional
public class AreaDicServiceImpl implements AreaDicService {
	@Autowired
	private AreaDicDao areaDicDao;

	/**
	 * <b>根据城市是否是国内查询热门城市</b>
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	public List<AreaDic> getHotCityByType(Integer isChina) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("isChina", isChina);
		queryMap.put("isHot", 1);
		queryMap.put("isActivated", 1);
		// 返回查询结果
		return areaDicDao.getHotCityByQuery(queryMap);
	}

	/**
	 * <b>根据城市查询商圈</b>
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	public List<Object> getTradearea(Long cityId) throws Exception {
		// 封装查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("parentId", cityId);
		queryMap.put("isActivated", 1);
		queryMap.put("isTradingArea", 1);
		// 返回查询结果
		return areaDicDao.findTradeareaByQuery(queryMap);
	}
}
