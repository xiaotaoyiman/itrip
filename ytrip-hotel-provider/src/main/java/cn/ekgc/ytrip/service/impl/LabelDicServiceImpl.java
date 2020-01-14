package cn.ekgc.ytrip.service.impl;

import cn.ekgc.ytrip.dao.LabelDicDao;
import cn.ekgc.ytrip.pojo.entity.LabelDic;
import cn.ekgc.ytrip.service.LabelDicService;
import feign.QueryMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>通用字典业务层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Service("labelDicService")
@Transactional
public class LabelDicServiceImpl implements LabelDicService {
	@Autowired
	private LabelDicDao labelDicDao;

	/**
	 * <b>查询酒店特色列表</b>
	 * @return
	 * @throws Exception
	 */
	public List<LabelDic> getHotelFeature() throws Exception {
		// 封装查询参数
		Map<String, Object> qureyMap = new HashMap<String, Object>();
		qureyMap.put("parentId", 16);
		// 返回查询结果
		return labelDicDao.findHotelFeatureByQuery(qureyMap);
	}

	/**
	 * <b>查询酒店房间床型列表</b>
	 * @return
	 * @throws Exception
	 */
	public List<LabelDic> queryhotelroombed() throws Exception {
		// 封装查询参数
		Map<String, Object> qureyMap = new HashMap<String, Object>();
		qureyMap.put("parentId", 1);
		// 返回查询结果
		return labelDicDao.findHotelFeatureByQuery(qureyMap);
	}
}
