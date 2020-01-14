package cn.ekgc.ytrip.util;

/**
 * <b>生成订单编号工具类</b>
 */
public class OrderNoUtil {

	/**
	 * <b>生成订单编号</b>
	 * <p>订单编号生成规则：MD5(当前时间毫秒数 + hotelId + Random + roomId)</p>
	 * @param hotelId
	 * @param roomId
	 * @return
	 */
	public static String createUserNo(Long hotelId, Long roomId) {
		StringBuffer sb = new StringBuffer();
		sb.append(System.currentTimeMillis());
		sb.append(hotelId);
		sb.append(Math.round(Math.random() * 1000));
		sb.append(roomId);
		String orderNo = MD5Util.encrypt(sb.toString());
		return orderNo;
	}
}
