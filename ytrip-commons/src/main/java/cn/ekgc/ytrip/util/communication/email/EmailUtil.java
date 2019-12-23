package cn.ekgc.ytrip.util.communication.email;

import cn.ekgc.ytrip.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component("emailUtil")
public class EmailUtil {
	@Autowired
	private JavaMailSender mailSender;

	// 异步发送验证码
	@Async
	public void sendEmail(String email, String activeCode) {
		// 创建邮件发送对象，使用MimeMailMessage对象可以发送HTML格式的邮件
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 设置发送人
		mailMessage.setFrom(ConstantUtil.MAIL_FROM);
		// 设置收件人
		mailMessage.setTo(email);
		// 设置发送主题
		mailMessage.setSubject("爱旅行用户注册激活码");
		// 发送内容主体
		mailMessage.setText("您的账户的激活码是：" + activeCode + "，请在" + ConstantUtil.ACTIVE_CODE_TIMEOUT + "分钟内登录，输入验证码激活您的账户");
		// 发送邮件
		mailSender.send(mailMessage);
	}
}
