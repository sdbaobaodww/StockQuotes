package com.duan.quotes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duan.quotes.pojo.Stock;

public class StockDao extends BaseDao {

	public List<Stock> loadAllStock() {
		return querySession.createQuery("from Stock").list();
	}

	public void saveStock(Stock stock) {
		Session se = this.getSession();
		Transaction tx = se.beginTransaction();
		se.save(stock);
		tx.commit();
	}
}
