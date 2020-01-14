package cn.ekgc.ytrip.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date parseDate(String str) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		str = str.substring(0, 10);
		Date date = dateFormat.parse(str);
		return date;
	}
}
