package com.tube.clone.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

@RestController
public class IndexController {
	
	@ApiOperation(value = "Get Index List", notes = "첫 화면 목록 가져오기")
	@RequestMapping(value = "/getIndexList", method = RequestMethod.POST)
	@ApiImplicitParam(examples = @Example(value= {  
			@ExampleProperty(mediaType = "application/json", value = "{\"key\":\"value\"}") }))
	public List<Map<String, String>> getIndexList(
			Map<String, String> param){
		Map<String, String> item = new HashMap<String, String>();
		item.put("key", "value");
		
		
		List<Map<String, String>> items = new ArrayList<>();
		items.add(item);
		
		return items;
	}
}
