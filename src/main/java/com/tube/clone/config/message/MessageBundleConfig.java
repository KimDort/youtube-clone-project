package com.tube.clone.config.message;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.tube.clone.config.message.domain.MessageDomain;
import com.tube.clone.config.message.repositories.MessageRepositories;

@Configuration
public class MessageBundleConfig extends ReloadableResourceBundleMessageSource{
	
	@Autowired
	private MessageRepositories messageRepo;

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		MessageDomain msgDomain = new MessageDomain();
		msgDomain.setMessageCodeName(code);
		msgDomain.setMessageCodeLocale(locale.getCountry());
		
		msgDomain = messageRepo.findByMessageCodeNameAndMessageCodeLocale(code, locale.getCountry());
		
		MessageFormat format;
		
		if(msgDomain != null) {
			format = new MessageFormat(msgDomain.getMessageCodeContent(), locale);
		}else {
			format = super.resolveCode(code, locale);
		}
		
		return format;
	}
	
}
