package cn.ekgc.ytrip.controller;

import cn.ekgc.ytrip.base.controller.BaseController;
import cn.ekgc.ytrip.base.enums.SuccessEnum;
import cn.ekgc.ytrip.hotelTransport.HotelOrderTransport;
import cn.ekgc.ytrip.pojo.entity.HotelOrder;
import cn.ekgc.ytrip.pojo.entity.User;
import cn.ekgc.ytrip.pojo.vo.PersonalOrderRoomVO;
import cn.ekgc.ytrip.pojo.vo.ResponseResult;
import cn.ekgc.ytrip.userTransport.UserTransport;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController("hotelController")
@RequestMapping("/trade/api")
public class HotelTradeController extends BaseController {
	@Autowired
	private HotelOrderTransport hotelOrderTransport;
	@Autowired
	private UserTransport userTransport;

	@RequestMapping(value = "/prepay/{orderNo}", method = RequestMethod.GET)
	public ResponseResult<Object> test(@PathVariable("orderNo") String orderNo) throws Exception {
		// 根据订单编号查询订单信息
		HotelOrder hotelOrder = hotelOrderTransport.getHotelOrderByNo(orderNo);
		// 判断订单是否存在，并且是未支付
		if (hotelOrder != null && hotelOrder.getOrderStatus() == 0) {
			// 查询订单酒店、房间信息
			PersonalOrderRoomVO vo = hotelOrderTransport.getPersonalOrderRoomInfo(hotelOrder.getId());
			// 设定订单交易编号
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String outTradeNo = simpleDateFormat.format(new Date()) + String.valueOf(System.currentTimeMillis());
			// 订单金额
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			String total_amount = decimalFormat.format(vo.getPayAmount());
			AlipayClient alipayClient = new DefaultAlipayClient(
					"https://openapi.alipaydev.com/gateway.do",
					"2016101900725696",
					"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDKk7dk+4Rl5Z8x71v/EYEBK6/WKu+4DKV/utrujqwWBoal7yd398AFj3o61g+h592hIygBuRjo/JVt4hidIeiDM29Sk8jIA4voSh4SzD25OzW7rPsOL9NGDq4I9i63Z7eNKq1TdO5GpTfNs8v2UqzjXpxPlkg37q8CBpg5m3ae/z/E8BOWTPcJcXyYIwrTAE2a94mc/wAU1IAZHX1uDMHtR00XMNavg9Pkjb9lBXLpEGcix5xUX3naj5TJ6WLLbJCbAtcfpitfG6edU2zFKadAuwLUiGPgDSv4tEaAsIwZofNNVSm8PH5QuLotE4ZFFYJMvaRRnDVw9xm3G2M5yfLDAgMBAAECggEBAJtH98BUe/yPsyPX0rEGfdij4DlO2EpAh2wNk1xhyM9pWTVAyQRQyMR6YrtO8tSso1qtVDFd7RZK51A/i5aHM2++sYqe9b072rbIeZz3KikeleWyhw8s4cpF3mnIK68ytaRkHlBmxW8pnkjzlVjPru7qyu7Gx7z4HPZ0j6M45AijSk6dHWWqeDoxyN2/bCBAHHq3TZElZ9jBQTp5tO9BM06QDKbHQcSHWAJmVpMrDPFmpN+nP4ynaVrH8C25a+D8BtmWmRgC47ZCYzARNwr5X9jAzha/X1TZ7FExNDCpnBitVa0ZU2KBz/rr9sfv92o27wjmxBrhCb5g4djpa8OJZkECgYEA8TbaIryN1tkDKOru+mn1bPdI+1ul/wDOMHn7nQdM9QGFr14oBvfKt6JkDlrs16Onwpav7Y56ajUXRC+nqN6hUOueXjW/E3Bb1B2VRcUCGZTs2YahuFx71kRj1J1KlO6Q3EhVbDcLpWz886VpekZk0zqpnFFc6Kzt5DiRnVAGrTsCgYEA1v6Q/2kq59MajeUikLUiS3TXTSrj1UhnxrR//dSl0YdjyyTTCUMfRWzAxW+ZXs99myizVl55BE1DQtr8zO5ITd0v/aO95jXwbyMYrmi92V2cGssXQRwC/SQsN0FG4FjcoWSFwuHPA0OEZ4q/ovdr7Eiyl3QkxwKkPzVNjlJYmBkCgYAWIZ9AAqbM6JpWnb+RcDlZZxLmJRGE5REF95szXCEedaZLYcswYHnUAW1Gt+b1HgndW7AGjsUCi5533ht27f5FC2k7YZkHco6qVpCElz/LExPsHWMnJC0kip/SOSBHq9/UpjpYNHC4G9jVjf6wflbv3nkDY5+vpXYqM1qA4X/LUwKBgAszG66Sde+5qYp2w0Qyi2TO42BbuZ6ktPM+9+PrLfAqU2KDupKi8oAVWD1wwNaVjH+GsUDwjvFQbQ6QhdbPGukjepqF6yCQ8sN3WpPLVQh7veQPbFix8t3pAnymY+DQAD5QFD/Ar54F6GHUcoM0zGR0rQkCvd+4HYJFbpLZa1ARAoGAaSM2lrD0l9k5vP4Wi6xTOJVLe19T6qitC8Y4hnyQaU5xN4OI6rIbo4oHk1Y9jpAfrM5u7zPqjwQEkGZu+y1AQiK89RO1HpM0cyMbgQMKPgMsqoCRD9yZ5sY5e7VLrQkYqE22ylHE4WPHAUN07zyQ7fLxZbb8EPbI72eanO4/Rbc=",
					"json",
					"UTF-8",
					"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAypO3ZPuEZeWfMe9b/xGBASuv1irvuAylf7ra7o6sFgaGpe8nd/fABY96OtYPoefdoSMoAbkY6PyVbeIYnSHogzNvUpPIyAOL6EoeEsw9uTs1u6z7Di/TRg6uCPYut2e3jSqtU3TuRqU3zbPL9lKs416cT5ZIN+6vAgaYOZt2nv8/xPATlkz3CXF8mCMK0wBNmveJnP8AFNSAGR19bgzB7UdNFzDWr4PT5I2/ZQVy6RBnIsecVF952o+Uyeliy2yQmwLXH6YrXxunnVNsxSmnQLsC1Ihj4A0r+LRGgLCMGaHzTVUpvDx+ULi6LROGRRWCTL2kUZw1cPcZtxtjOcnywwIDAQAB",
					"RSA2"); //获得初始化的AlipayClient
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
			alipayRequest.setReturnUrl("http://itrip.project.bdqn.cn/trade/api/notify?orderId=" + hotelOrder.getId() + "&tradeNo=" + outTradeNo + "&payType=1");
			String json = "{\"out_trade_no\":\"" + outTradeNo
					+ "\", \"product_code\":\"FAST_INSTANT_TRADE_PAY\", "
					+ "\"total_amount\":\"" + total_amount
					+ "\",\"subject\":\"" + vo.getHotelName()
					+ "\",\"body\":\"" + vo.getRoomTitle() + "," + vo.getCount() + "间," + vo.getBookingDays() + "天," + "\","
					+ "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\",\"extend_params\":{}}";
			alipayRequest.setBizContent(json);//填充业务参数
			String form="";
			try {
				form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(form);//直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
	}

	@RequestMapping(value = "/notify", method = RequestMethod.GET)
	public ResponseResult<Object> notify(Long orderId, String tradeNo, Integer payType) throws Exception {
		// 得到当前登录用户信息
		Cookie[] cookies = request.getCookies();
		String userCode = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				userCode = cookie.getValue();
			}
		}
		// 获得当前登录用户
		User user = userTransport.getUserByUserCode(userCode);
		HotelOrder hotelOrder = new HotelOrder();
		hotelOrder.setOrderStatus(2);
		hotelOrder.setId(orderId);
		hotelOrder.setTradeNo(tradeNo);
		hotelOrder.setPayType(payType);
		hotelOrder.setModifiedBy(user.getId());
		hotelOrder.setModifyDate(new Date());
		// 修改订单编号
		boolean flag = hotelOrderTransport.updateHotelOrder(hotelOrder);
		if (flag) {
			response.sendRedirect("http://localhost/itrip");
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE);
	}
}
