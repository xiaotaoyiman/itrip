package cn.ekgc.ytrip.util;

import javax.print.DocFlavor;
import java.util.Random;

public class ProduceActiveCode {

	/**
	 * <b>随机生成4位数字的激活码</b>
	 * @return
	 * @throws Exception
	 */
	public static String getActiveCode() throws Exception {
		Random rd =new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			int code = rd.nextInt(10);
			sb.append(code);
		}
		return sb.toString();
	}
}
