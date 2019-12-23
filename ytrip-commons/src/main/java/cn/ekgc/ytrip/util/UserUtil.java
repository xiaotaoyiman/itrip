package cn.ekgc.ytrip.util;

import com.netflix.eureka.cluster.PeerEurekaNode;

import java.util.HashMap;

/**
 * <b>用户工具类</b>
 * @version 3.0.0 2019-12-12
 * @since 3.0.0
 */
public class UserUtil {
	// email验证的正则表达式
	private static final String EMAILEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	// 手机号码验证的正则表达式
	private static final String CELLPHONEEX = "^1[0-9]{10}$";

	/**
	 * <b>检验用户输入的userCode的有效性</b>
	 * @param userCode
	 * @return
	 */
	public static boolean checkUserCode(String userCode) {
		// 判断输入的userCode的有效性
		if (userCode != null && !"".equals(userCode.trim())) {
			// 使用正则表达式匹配
			if (userCode.matches(EMAILEX) || userCode.matches(CELLPHONEEX)) {
				return true;
			}
		}
		return false;
	}
}
