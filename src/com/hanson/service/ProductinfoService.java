package com.hanson.service;

import com.hanson.model.Productinfo;


public interface ProductinfoService {
	/**
	 * 保存产品附件信息
	 * @param productInfo
	 */
	public void saveProductinfo(Productinfo productinfo);
	
	/**
	 * 根据产品id查询产品附加信息
	 * @param productinfo
	 * @return
	 */
	public Productinfo findProductinfoByPId(Productinfo productinfo);
	
	/**
	 * 修改产品附加表
	 * @param productinfo
	 */
	public void updateProductinfo(Productinfo productinfo);
	

}
