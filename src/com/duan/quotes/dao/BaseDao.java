package com.duan.quotes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDao {

	protected Session querySession;

	public BaseDao() {
		querySession = HibernateUtils.getSession();
	}

	public Session getSession() {
		return HibernateUtils.getSessionFactory().getCurrentSession();
	}

	public void save(Object obj) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(obj);
		tx.commit();
	}

	public void saveList(List objs) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		for (Object obj : objs) {
			session.save(obj);
		}
		tx.commit();
	}

	public void closeSession() {
		querySession.close();
	}
}
