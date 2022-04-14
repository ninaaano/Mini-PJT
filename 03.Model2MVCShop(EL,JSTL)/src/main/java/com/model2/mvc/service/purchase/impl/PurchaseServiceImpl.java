package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDao;

public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseDao purchaseDao;

	public PurchaseServiceImpl() {
		purchaseDao = new PurchaseDao();
	}

	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDao.insertPurchase(purchase);

	}

	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDao.findPurchase(tranNo);
	}

	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		return purchaseDao.getPurchaseList(search,buyerId);
	}

	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDao.updatePurchase(purchase);

	}

	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);
	}

}
