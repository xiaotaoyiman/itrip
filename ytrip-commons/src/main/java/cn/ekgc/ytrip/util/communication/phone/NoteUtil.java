package cn.ekgc.ytrip.util.communication.phone;

import cn.ekgc.ytrip.util.ConstantUtil;
import com.cloopen.rest.sdk.CCPRestSDK;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component("noteUtil")
public class NoteUtil {

	// 异步发送验证码
	@Async("asyncServiceExecutor")
	public void sendNote(String userCode, String activeCode) {
		HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(ConstantUtil.SERVERIP, ConstantUtil.SERVERPORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(ConstantUtil.ACCOUNT_SID, ConstantUtil.AUTH_TOKEN);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(ConstantUtil.APPID);// 初始化应用ID
		result = restAPI.sendTemplateSMS(userCode,ConstantUtil.TEMPID ,new String[]{activeCode, String.valueOf(ConstantUtil.ACTIVE_CODE_TIMEOUT)});

		System.out.println("SDKTestSendTemplateSMS result=" + result);

		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
	}
}
