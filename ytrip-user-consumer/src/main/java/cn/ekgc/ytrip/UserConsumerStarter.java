package cn.ekgc.ytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>用户模块Consumer启动类</b>
 * @version 4.0.0 2019-12-23
 * @since 3.0.0
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class UserConsumerStarter {
	public static void main(String[] args) {
		SpringApplication.run(UserConsumerStarter.class, args);
	}
}
