package com.model2.mvc.service.product.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDao;

public class ProductServiceImpl implements ProductService{
	
	private ProductDao productDao;
	
	public ProductServiceImpl() {
		productDao = new ProductDao();
	}
	
	public void addProduct(Product product) throws Exception{
		productDao.insertProduct(product);
		
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return productDao.findProduct(prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProductList(search);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.updateProduct(product);
		
	}

}
