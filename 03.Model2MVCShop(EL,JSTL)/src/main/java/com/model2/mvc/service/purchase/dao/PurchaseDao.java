package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDao {

	public PurchaseDao() {
	}

	public void insertPurchase(Purchase purchase) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "INSERT INTO TRANSACTION VALUES (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,?,sysdate)";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		stmt.setString(2, purchase.getBuyer().getUserId());
		stmt.setString(3, purchase.getPaymentOption());
		stmt.setString(4, purchase.getReceiverName());
		stmt.setString(5, purchase.getReceiverPhone());
		stmt.setString(6, purchase.getDivyAddr());
		stmt.setString(7, purchase.getDivyRequest());
		stmt.setString(8, purchase.getTranCode());
		stmt.setString(9, purchase.getDivyDate());

		System.out.println("����insert"); 
		
		stmt.executeUpdate();

		stmt.close(); 
		con.close();
	}

	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();

		Connection con = DBUtil.getConnection();

		String sql ="SELECT * FROM TRANSACTION WHERE BUYER_ID='"+buyerId+"' ORDER BY TRAN_NO";

//		if (search.getSearchCondition() != null) {
//			if (search.getSearchCondition().equals("0") && !search.getSearchCondition().equals("")) {
//				sql += "WHERE tran_no like '%" + search.getSearchKeyword() + "%'";
//			} else if (search.getSearchCondition().equals("1")) {
//				sql += "WHERE buyer_id like '%" + search.getSearchKeyword() + "%'";
//			} else if (search.getSearchCondition().equals("2") && !search.getSearchCondition().equals("")) {
//				sql += "WHERE prod_no like'%" + search.getSearchKeyword() + "%'";
//			}
//			}
//		}
//		sql += " order by tran_no";
		
		System.out.println("PurchaseDAO::Original SQL :: " + sql);

		int totalCount = this.getTotalCount(sql);
		System.out.println("PurchaseDAO :: totalCount  :: " + totalCount);

		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();

		User user = new User();
		Product product = new Product();

		List<Purchase> list = new ArrayList<Purchase>();
		while (rs.next()) {
//			User user = new User();
//			user.setUserId(buyerId);
//			
//			Product product=new Product();
//			product.setProdNo(Integer.parseInt(rs.getString("PROD_NO")));
//			
			Purchase purchase = new Purchase();
			purchase.setBuyer(user);
			purchase.setPurchaseProd(product);
			purchase.setTranNo(rs.getInt("TRAN_NO"));
			purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchase.setOrderDate(rs.getDate("ORDER_DATA"));
			purchase.setDivyDate(rs.getString("DLVY_DATE"));
			purchase.setDivyAddr(rs.getString("DEMAILADDR"));
			list.add(purchase);
			
		}

		map.put("totalCount", new Integer(totalCount));

		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		return map;
	}
	

	public Purchase findPurchase(int tranNo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "select*from transaction where tran_no=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);

		ResultSet rs = stmt.executeQuery();

		Purchase purchase = null;
		
		Product product=new Product();
		product.setProdNo(rs.getInt("prod_no"));
		//product.getProdNo();
		
		User user=new User();
		user.setUserId(rs.getString("buyer_id"));
		//user.getUserId();
		
		while (rs.next()) {
			

			purchase = new Purchase();
			purchase.setBuyer(user);
			purchase.setTranNo(rs.getInt("TRAN_NO"));
			purchase.setPurchaseProd(product);
			purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchase.setOrderDate(rs.getDate("ORDER_DATA"));
			purchase.setDivyDate(rs.getString("DLVY_DATE"));
			purchase.setDivyAddr(rs.getString("DEMAILADDR"));

		}
		rs.close();
		stmt.close();
		con.close(); 

		return purchase;
	}

	public void updatePurchase(Purchase purchase) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "UPDATE transaction\r\n"
				+ "set payment_option=?, receiver_name=?,receiver_phone=?,demailaddr=?,dlvy_request=?,dlvy_date=?\r\n"
				+ "where tran_no=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchase.getPaymentOption());
		stmt.setString(2, purchase.getReceiverName());
		stmt.setString(3, purchase.getReceiverPhone());
		stmt.setString(4, purchase.getDivyAddr());
		stmt.setString(5, purchase.getDivyRequest());
		stmt.setString(6, purchase.getDivyDate());
		stmt.setInt(7, purchase.getTranNo());

		stmt.executeUpdate();

		stmt.close();
		con.close();
	}

	public void updateTranCode(Purchase purchase) throws Exception {

		Connection con = DBUtil.getConnection();
		String sql = "UPDATE transaction SET tran_status_code=? WHERE tran_no=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, purchase.getTranNo());
		stmt.setString(2,purchase.getTranCode());

		stmt.executeUpdate();

		stmt.close();
		con.close();
	}

	private int getTotalCount(String sql) throws Exception {
		sql = "SELECT COUNT(*) " + "FROM ( " + sql + ") countTable";
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();

		int totalCount = 0;
		if (rs.next()) {
			totalCount = rs.getInt(1);
		}

		pStmt.close();
		con.close();
		rs.close();

		return totalCount;
	}

	private String makeCurrentPageSql(String sql, Search search) {
		sql = "SELECT * " + "FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " + " 	FROM (	" + sql
				+ " ) inner_table " + "	WHERE ROWNUM <=" + search.getCurrentPage() * search.getPageSize() + " ) "
				+ "WHERE row_seq BETWEEN " + ((search.getCurrentPage() - 1) * search.getPageSize() + 1) + " AND "
				+ search.getCurrentPage() * search.getPageSize();

		System.out.println("PurchaseDAO :: make SQL :: " + sql);

		return sql;
	}
}
