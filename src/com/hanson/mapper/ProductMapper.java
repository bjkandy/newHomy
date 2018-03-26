package com.hanson.mapper;

import java.util.List;
import java.util.Map;

import com.hanson.model.Product;
import com.hanson.model.ProductVO;

public interface ProductMapper {
	
	public Product findUniqueByQyid(Long id);
	
	public Product findUniqueById(Long id);
	
	public long save(Product bean);
	
	/**
	 * 分页查询-查询数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> search(Map<String, Object> map);
	
	/**
	 * 分页查询-统计总数
	 * @param map
	 * @return
	 */
	public long countTotles(Map<String, Object> map);
	
	/**
	 * 删除产品
	 * @param product
	 */
	public void deleteProduct(Product product);
	
	/**
	 * 保存产品
	 * @param bean
	 * @return
	 */
	public Integer saveProduct(ProductVO productVO);
	
	public List<Map<String ,Object>> saveProductList(List<Map<String ,Object>> productVOList);
	
	/**
	 * 查询汇美创建的相框
	 * @param productVO
	 * @return
	 */
	public List<ProductVO> findPhotoFrame();
	
	public List<ProductVO> FindalbumProduct();
	/**
	 * 根据ID查找产品
	 * @param productVO
	 * @return
	 */
	public ProductVO findProductById(ProductVO productVO);
	
	/**
	 * 修改产品
	 * @param productVO
	 * @return
	 */
	public Integer updateProduct(ProductVO productVO);
	
	/**
	 * 根据相框名称批量更新
	 * @param productVO
	 * @return
	 */
	public Integer updateProductByName(ProductVO productVO);
	/**
	 * 导出产品数据excel
	 * @param data
	 * @return
	 */
	public List<Map<String,Object>> productExcel(Map<String,Object> data);
	public List<ProductVO> FindStoreProductbywhere(Map<String,Object> map);
	
	public ProductVO SelectProductByName(Map<String,Object> map);
	/**
	 * 导出产品数据详情excel
	 * @param data
	 * @return
	 */
	public List<Map<String,Object>> productInfoExcel(Map<String,Object> data);
	
    
  
	public List<Map<String,Object>> getProductsList(Map map);
	
	
	public List<Map<String,Object>> getAllCategories();
	
	public Integer getProductCount(Map map);
	
	public List<Map<String,Object>> getCategoryNameAndId();
}
