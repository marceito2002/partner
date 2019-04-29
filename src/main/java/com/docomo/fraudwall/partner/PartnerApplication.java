package com.docomo.fraudwall.partner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PartnerApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(PartnerApplication.class, args);
	}

}
