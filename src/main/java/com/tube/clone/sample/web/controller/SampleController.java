package com.tube.clone.sample.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tube.clone.sample.service.SampleService;

@RestController
public class SampleController {
	
	@Autowired
	private SampleService service;
	
	@RequestMapping("/getSample")
	public Map<String, Object> getSample(@RequestBody Map<String, Object> param){
		return service.getSample(param);
	}
}
