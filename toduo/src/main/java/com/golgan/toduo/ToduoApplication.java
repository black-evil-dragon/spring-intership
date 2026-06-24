package com.golgan.toduo;

import com.golgan.toduo.core.services.LoggerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ToduoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToduoApplication.class, args);
	}
}
