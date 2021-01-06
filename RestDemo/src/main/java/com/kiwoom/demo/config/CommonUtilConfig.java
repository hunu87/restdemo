package com.kiwoom.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonUtilConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
