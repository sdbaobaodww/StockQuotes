package com.duan.quotes.pack;

import org.hibernate.Criteria;

public interface Pack {
	public String toSqlString();

	public void addToCriteria(Criteria criteria);
}
