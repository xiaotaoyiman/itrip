package cn.ekgc.ytrip.service;

import cn.ekgc.ytrip.pojo.entity.LabelDic;

import java.util.List;

/**
 * <b>通用字典业务层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
public interface LabelDicService {

	/**
	 * <b>查询酒店特色列表</b>
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> getHotelFeature() throws Exception;

	/**
	 * <b>查询酒店房间床型列表</b>
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> queryhotelroombed() throws Exception;
}
