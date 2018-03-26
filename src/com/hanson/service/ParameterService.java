package com.hanson.service;

import java.util.List;

import com.hanson.model.Parameter;


public interface ParameterService {
	//查询参数信息
	public List<Parameter> findParameters(Parameter parameter);
}
