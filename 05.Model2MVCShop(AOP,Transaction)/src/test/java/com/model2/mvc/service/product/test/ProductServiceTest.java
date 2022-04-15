package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })

public class ProductServiceTest {

	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("��ü��");
		product.setPrice(6000);
		product.setProdDetail("����");
		product.setManuDate("maunDate");
		product.setFileName("���������̹���");
		
		productService.addProduct(product);
		
		product = productService.getProduct(10023);
		
		System.out.println(product);
	
	}
	
	@Test
	public void testGetProduct() throws Exception {
		Product product = new Product();
		
		product = productService.getProduct(10023);
		
//		Assert.assertEquals("��ü��",product.getProdName());
//		Assert.assertEquals("6000",product.getPrice());
//		Assert.assertEquals("����",product.getProdDetail());
//		Assert.assertEquals("manuDate",product.getManuDate());
//		Assert.assertEquals("���������̹���",product.getFileName());
		
		System.out.println("get : "+product);
	}
	
	@Test
		 public void testUpdateProduct() throws Exception{
			 
			Product product = productService.getProduct(10023);
			Assert.assertNotNull(product);
			
//			Assert.assertEquals("��ü��",product.getProdName());
//			Assert.assertEquals("6000",product.getPrice());
//			Assert.assertEquals("����",product.getProdDetail());
//			Assert.assertEquals("manuDate",product.getManuDate());
//			Assert.assertEquals("���������̹���",product.getFileName());

			product.setProdName("�ٴҶ��");
			product.setPrice(5500);
			product.setProdDetail("change");
			product.setManuDate("220407");
			product.setFileName("�����־�̴»���");
			
			productService.updateProduct(product);
			
			product = productService.getProduct(10023);
//			Assert.assertNotNull(product);
			
			
			System.out.println("���� : " +product);
		 }
	
		
		 @Test
		 public void testGetProductListAll() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(3, list.size());
		 	
			
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword("");
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(3, list.size());
		 	

		 	System.out.println("����Ʈ : "+list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }
		 
		 @Test
		 public void testGetProductListByProdNo() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword("");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(3, list.size());
		 	
			//==> console Ȯ��
		 	//System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console Ȯ��
		 	//System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }
		 
		 @Test
		 public void testGetProductListByProductName() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword("��Ʈ��");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console Ȯ��
		 	System.out.println("�̸� : "+list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console Ȯ��
		 	System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }	 

}
