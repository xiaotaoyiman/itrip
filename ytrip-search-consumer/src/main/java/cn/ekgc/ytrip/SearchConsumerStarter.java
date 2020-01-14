package cn.ekgc.ytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>搜索模块Consumer启动类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class SearchConsumerStarter {
	public static void main(String[] args) {
		SpringApplication.run(SearchConsumerStarter.class, args);
	}
}
