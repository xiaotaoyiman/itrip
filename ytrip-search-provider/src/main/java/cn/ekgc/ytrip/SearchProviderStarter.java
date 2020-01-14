package cn.ekgc.ytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <b>搜索模块Provider启动类</b>
 * @version 4.0.0
 * @since 3.0.0
 */
@EnableEurekaClient
@SpringBootApplication
public class SearchProviderStarter {
	public static void main(String[] args) {
		SpringApplication.run(SearchProviderStarter.class, args);
	}
}
