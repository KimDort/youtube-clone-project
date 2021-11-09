package com.tube.clone.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@RequestMapping("/getIndexList")
	public List<Map<String, Object>> getIndexList(){
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("key", "value");
		
		
		List<Map<String, Object>> items = new ArrayList<>();
		items.add(item);
		
		return items;
	}
}
