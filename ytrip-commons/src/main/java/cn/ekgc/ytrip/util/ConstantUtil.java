package cn.ekgc.ytrip.util;

import com.netflix.discovery.StatusChangeEvent;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Properties;

public class ConstantUtil {
	private static Properties props = new Properties();

	static {
		try {
			props.load(ConstantUtil.class.getClassLoader().getResourceAsStream("ytrip.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// redis过期时间
	public static final Long ACTIVE_CODE_TIMEOUT = Long.parseLong(props.getProperty("active.code.timeout"));
	// 邮件发送者
	public static final String MAIL_FROM = props.getProperty("mail.from");

	public static final String AUTH_SECRET = props.getProperty("auth.secret");
	public static final String SERVERIP = props.getProperty("serverIP");
	public static final String SERVERPORT = props.getProperty("serverPort");
	public static final String ACCOUNT_SID = props.getProperty("account.sid");
	public static final String AUTH_TOKEN= props.getProperty("auth.token");
	public static final String APPID = props.getProperty("appid");
	public static final String TEMPID = props.getProperty("tempId");
}
