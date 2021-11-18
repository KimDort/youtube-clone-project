package com.tube.clone.config.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

@Configuration
public class MessageConfig {
	@Autowired
	private MessageSource message;
	
	@Bean
	public MessageSourceAccessor accessor() {
		return new MessageSourceAccessor(message);
	}
}
