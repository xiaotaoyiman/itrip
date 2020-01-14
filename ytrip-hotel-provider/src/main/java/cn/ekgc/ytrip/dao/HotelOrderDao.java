package cn.ekgc.ytrip.dao;

import cn.ekgc.ytrip.pojo.entity.HotelOrder;
import cn.ekgc.ytrip.pojo.entity.OrderLinkUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>酒店订单数据持久层接口</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@Repository
public interface HotelOrderDao {

	/**
	 * <b>酒店订单数量表</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer findHotelOrderCountByQuery(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>生成订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	boolean addHotelOrder(HotelOrder hotelOrder) throws Exception;

	/**
	 * <b>保存订单联系人</b>
	 * @param orderLinkUser
	 * @return
	 * @throws Exception
	 */
	boolean addOrderLinkUser(OrderLinkUser orderLinkUser) throws Exception;

	/**
	 * <b>根据条件查询订单列表</b>
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<HotelOrder> findHotelOrderByQuery(Map<String, Object> paramMap) throws Exception;

	/**
	 * <b>根据条件查询订单联系人列表</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<OrderLinkUser> findOrderLinkUserByQuery(Map<String, Object> queryMap) throws Exception;

	/**
	 * <b>修改订单信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	boolean updateHotelOrder(HotelOrder hotelOrder) throws Exception;
}
