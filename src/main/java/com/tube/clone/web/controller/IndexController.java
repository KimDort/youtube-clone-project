package com.tube.clone.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tube.clone.config.DummyDomain;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

@RestController
public class IndexController {

	@RequestMapping(value = "/getIndexList", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Get Index List", notes = "첫 화면 목록 가져오기")
	@ApiImplicitParams(@ApiImplicitParam(name = "param"
		,dataTypeClass = DummyDomain.class
		,examples = @Example(@ExampleProperty(mediaType = "application/json", value = "{\"key\":\"value1\"}"))))
	public List<Map<String, Object>> getIndexList(@RequestBody Map<String, Object> param) {
		System.out.println("jsonNode : " + param);
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("key", "value");

		List<Map<String, Object>> items = new ArrayList<>();
		items.add(item);

		return items;
	}
}
