package cn.ekgc.ytrip.service.impl;

import cn.ekgc.ytrip.pojo.vo.HotelVO;
import cn.ekgc.ytrip.pojo.vo.Page;
import cn.ekgc.ytrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.ytrip.pojo.vo.SearchHotelVO;
import cn.ekgc.ytrip.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.bouncycastle.voms.VOMSAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>搜索模块业务层接口实现类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Service("searchService")
@Transactional
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SolrClient solrClient;

	/**
	 * <b>使用solr查询热门城市酒店</b>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<HotelVO> searchItripHotelListByHotCity(SearchHotCityVO vo) throws Exception {

		HttpSolrClient httpSolrClient = (HttpSolrClient) solrClient;
		httpSolrClient.setParser(new XMLResponseParser());
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("cityId:" + vo.getCityId());
		// 设定连续查询条数
		solrQuery.setRows(vo.getCount());
		// 进行查询，获得结果
		QueryResponse queryResponse = httpSolrClient.query(solrQuery);
		// 通过QueryResponse获得结果
		List<HotelVO> list = queryResponse.getBeans(HotelVO.class);
		return list;
	}


	public Page<List<HotelVO>> searchItripHotelPage(SearchHotelVO vo) throws Exception {
		HttpSolrClient httpSolrClient = (HttpSolrClient) solrClient;
		httpSolrClient.setParser(new XMLResponseParser());
		SolrQuery solrQuery = new SolrQuery();
		/*StringBuffer tempQuery = new StringBuffer();
		int tempFlag = 0;
		if (vo != null) {
			if (vo.getDestination() != null) {
				tempQuery.append(" destination:" + vo.getDestination());
				tempFlag = 1;
			}
			if (vo.getHotelLevel() != null) {
				solrQuery.addFilterQuery("hotelLevel:" + vo.getHotelLevel() + "");
			}
			if (vo.getKeywords() != null) {
				if (tempFlag == 1) {
					tempQuery.append(" AND keyword:" + vo.getKeywords());
				} else {
					tempQuery.append(" keyword:" + vo.getKeywords());
				}
			}
			if (vo.getFeatureIds() != null) {
				StringBuffer stringBuffer = new StringBuffer("(");
				int flag = 0;
				String featureIdArray[] = vo.getFeatureIds().split(",");
				for (String featureId : featureIdArray) {
					if (flag == 0) {
						stringBuffer.append(" featureIds:" + "*," + featureId + ",*");
					} else {
						stringBuffer.append(" OR featureIds:" + "*," + featureId + ",*");
					}
					flag++;
				}
				stringBuffer.append(")");
				solrQuery.addFilterQuery(stringBuffer.toString());
			}
			if (vo.getMaxPrice() != null) {
				solrQuery.addFilterQuery("minPrice:" + "[* TO " + vo.getMaxPrice() +"]");
			}
			if (vo.getMinPrice() != null) {
				solrQuery.addFilterQuery("minPrice:" + "[" + vo.getMinPrice() +" TO *]");
			}
			if (vo.getAscSort() != null) {
				solrQuery.addSort(vo.getAscSort(), SolrQuery.ORDER.asc);
			}
			if (vo.getDescSort() != null) {
				solrQuery.addSort(vo.getDescSort(), SolrQuery.ORDER.desc);
			}
		}
		if (tempQuery.toString() != null) {
			solrQuery.setQuery(tempQuery.toString());
		}*/
		// 设置查询起始页
		// Integer rows = vo.getPageSize();
		// Integer currPage = vo.getPageNo();
		solrQuery.setStart(1);
		// 设置一页显示数量
		solrQuery.setRows(10);
		// 进行查询，获得结果
		StringBuffer tempQuery = new StringBuffer();
		if (vo.getDestination() != null) {
			tempQuery.append("destination:" + vo.getDestination());
		}
		if (vo.getFeatureIds() != null) {
			tempQuery.append("and featureIds:" + vo.getFeatureIds());
		}
		if (vo.getHotelLevel() != null) {
			tempQuery.append("and hotelLevel:" + vo.getHotelLevel());
		}
		solrQuery.setQuery(tempQuery.toString());
		QueryResponse queryResponse = httpSolrClient.query(solrQuery);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		List<HotelVO> list = queryResponse.getBeans(HotelVO.class);
		Page<List<HotelVO>> page = new Page<List<HotelVO>>(1, solrQuery.getRows(), new Long(solrDocumentList.getNumFound()).intValue());
		List<List<HotelVO>> hotelVOList = new ArrayList<List<HotelVO>>();
		for (HotelVO hotelVo : list) {
			List<HotelVO> tempList = new ArrayList<HotelVO>();
			tempList.add(hotelVo);
			hotelVOList.add(tempList);
		}
		page.setRows(hotelVOList);
		return page;
	}


}
