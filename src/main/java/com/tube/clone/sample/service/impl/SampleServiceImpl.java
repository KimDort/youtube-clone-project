package com.tube.clone.sample.service.impl;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import com.tube.clone.sample.service.SampleService;
import com.tube.clone.sample.service.mapper.SampleMapper;

@Service
public class SampleServiceImpl implements SampleService{
	
	@Autowired
	private SampleMapper mapper;
	
	@Autowired
	private MessageSourceAccessor msg;
	
	@Override
	public Map<String, Object> getSample(Map<String, Object> param, Locale locale) {
		System.out.println("get msg : "+msg.getMessage("MSG0001SYS"));
		return mapper.getSample(param);
	}

}
