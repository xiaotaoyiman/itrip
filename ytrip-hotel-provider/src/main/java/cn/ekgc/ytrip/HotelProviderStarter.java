package cn.ekgc.ytrip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <b>酒店模块Provider启动类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@EnableEurekaClient
@MapperScan("cn.ekgc.ytrip.dao")
@SpringBootApplication
public class HotelProviderStarter {
	public static void main(String[] args) {
		SpringApplication.run(HotelProviderStarter.class, args);
	}
}
