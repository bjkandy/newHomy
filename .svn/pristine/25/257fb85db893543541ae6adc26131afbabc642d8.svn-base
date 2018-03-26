package com.hanson.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.ParameterMapper;
import com.hanson.model.Parameter;
import com.hanson.service.ParameterService;

@Service
public class ParameterServiceImp implements ParameterService {
	@Autowired
	private ParameterMapper parameterMapper;

	@Override
	public List<Parameter> findParameters(Parameter parameter) {
		return parameterMapper.findParameters(parameter);
	}

}
