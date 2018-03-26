package com.hanson.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;




import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanson.common.util.Option;
import com.hanson.model.City;
import com.hanson.model.Province;
import com.hanson.service.ProvincialService;
import common.JsonUtils;

@Controller
@RequestMapping("/provincial")
public class ProvincialController {
	private static Logger logger = Logger.getLogger(ProvincialController.class);
	
	@Autowired
	private ProvincialService provincialService;
	
	/**
	 * 当传递city_code，则表明下拉框要被选中，否则不选中
	 */
	@RequestMapping("/citys")
	public void index(@RequestParam(value = "city_code", required = false) String city_code,
			@RequestParam(value = "pro_code", required = false) String pro_code, HttpServletResponse response) {
		try {
			logger.debug("获取所在地区" + city_code + ", 省" + pro_code);

			// 如果pro_code为””，则表明要获取城市菜单，否则获取市级菜单
			if (!pro_code.equals("")) {
				List<City> citys = provincialService.findCityByCode(pro_code);
				List<Option> coptions = new ArrayList<Option>(citys.size());

				for (City city : citys) {
					Option coption = new Option();
					coption.setId(city.getId().toString());
					coption.setName(city.getCityname());
					coption.setValue(city.getId().toString());

					// 市级菜单被选中
					if (city_code != null && !city_code.equals("")) {
						if (city.getId().longValue() == Long.valueOf(city_code).longValue()) {
							coption.setSelected("selected");
						}
					}
					coptions.add(coption);
				}
				renderJson(response, JsonUtils.toJson(coptions));
			} else {
				List<Province> provincials = provincialService.getProvincials();

				// 转换成标准的option属性（name,value,selected）
				List<Option> options = new ArrayList<Option>(provincials.size());

				// 被选中的省市
				// 则说明是展示页面，此时需要为省级菜单和市级菜单设置选择项
				if (city_code != null && !city_code.equals("")) {
					Province selected_provincial = provincialService.getProvincialByCitycode(city_code);

					pro_code = selected_provincial.getId().toString();
				} else {
					pro_code = provincials.get(0) == null ? "" : provincials.get(0).getId().toString();
				}

				for (Province provincial : provincials) {
					Option option = new Option();
					option.setId(provincial.getId().toString());
					option.setName(provincial.getProvincename());
					option.setValue(provincial.getId().toString());

					if (!pro_code.equals("") && provincial.getId().toString().equals(pro_code)) {
						option.setSelected("selected");
					}

					options.add(option);
				}

				renderJson(response, JsonUtils.toJson(options));
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getMessage(), e);

			renderJson(response, null);
		}
	}
	
	
	
	
	
	/**
     * 如果出错的话，response直接返回404
     */
    protected void renderJson(HttpServletResponse response, Object responseObject) {
        PrintWriter out = null;
        try {
            if (responseObject == null) {
                response.sendError(404);
                return;
            }
            // 将实体对象转换为JSON Object转换
            String responseStr = JsonUtils.toJson(responseObject);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");

            out = response.getWriter();
            out.append(responseStr);

            logger.debug("返回是：" + responseStr);
        } catch (IOException e) {
            logger.error(e.getMessage());
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}	
