package cn.ekgc.ytrip.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>用于生成和解析Token工具类</b>
 * @version 3.0.0 2019-12-16
 * @since 3.0.0
 */
public class JWTUtil {

	/**
	 * <b>根据用户信息，生成对应的Token JSON</b>
	 * @param userId
	 * @return
	 */
	public static String createToken(Long userId) {
		// 获得对于Token进行加密的算法对象
		Algorithm algorithm = Algorithm.HMAC256(ConstantUtil.AUTH_SECRET);
		// 创建JWT创建者JWTCreator.Builder
		JWTCreator.Builder builder = JWT.create();
		// 创建头部签名集合
		Map<String, Object> headerMap = new HashMap<String, Object>();
		headerMap.put("alg", "hmac256");
		headerMap.put("type", "jwt");
		// 设定头部签名信息
		builder.withHeader(headerMap);
		// 设定有效载荷信息
		builder.withClaim("id", userId);
		// 进行加密，获得最后的JSON
		return builder.sign(algorithm);
	}

	/**
	 * <b>对于给定的Token进行校验和解密，获得对应的信息</b>
	 * @param token
	 * @return
	 */
	public static Long decryptToken(String token) {
		// 设定加密时所用的算法构成
		Algorithm algorithm = Algorithm.HMAC256(ConstantUtil.AUTH_SECRET);
		// 校验所给定的Token是否正确
		try {
			JWTVerifier jwtVerifier = JWT.require(algorithm).build();
			// 如果能够成功解密，说明所提供的Token是正确的
			DecodedJWT decodedJWT = JWT.decode(token);
			// 通过解密后的JWT对象获得相应的荷载数据
			Long userId = decodedJWT.getClaim("id").asLong();
			if (userId != null && userId > 0) {
				return userId;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1L;
	}
}
