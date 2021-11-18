package com.tube.clone.sample.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tube.clone.sample.service.SampleService;
import com.tube.clone.sample.service.mapper.SampleMapper;

@Service
public class SampleServiceImpl implements SampleService{
	
	@Autowired
	private SampleMapper mapper;

	@Override
	public Map<String, Object> getSample(Map<String, Object> param) {
		
		return mapper.getSample(param);
	}

}
