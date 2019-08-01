package com.fdmgroup.OneDayProjectCoupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repos")
@EntityScan(basePackages = "entities")
public class OneDayProjectCouponApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneDayProjectCouponApplication.class, args);
	}

}
