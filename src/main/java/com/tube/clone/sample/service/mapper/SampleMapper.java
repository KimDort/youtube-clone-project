package com.tube.clone.sample.service.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleMapper {
	public Map<String, Object> getSample(Map<String, Object> param);
}