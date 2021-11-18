package com.tube.clone.config.message;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import com.tube.clone.config.message.domain.MessageDomain;
import com.tube.clone.config.message.repositories.MessageRepositories;

@Component("messageSource")
public class MessageBundleSource extends AbstractMessageSource{
	
	@Autowired
	private MessageRepositories messageRepo;

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		MessageDomain msgDomain = new MessageDomain();
		msgDomain = messageRepo.findByMessageCodeNameAndMessageCodeLocale(code, locale.getLanguage());
		
		MessageFormat format;
		
		if(msgDomain != null) {
			format = new MessageFormat(msgDomain.getMessageCodeContent(), locale);
		}else {
			format = new MessageFormat(code, locale);
		}
		
		return format;
	}
	
	
}
