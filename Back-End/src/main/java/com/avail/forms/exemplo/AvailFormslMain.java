package com.avail.forms.exemplo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.avail.forms.exemplo.utils.AppContext;

@SpringBootApplication
@EnableScheduling
public class AvailFormslMain {
	public static void main(String[] args) {
		SpringApplication.run(AppContext.class, args);
	}
}
