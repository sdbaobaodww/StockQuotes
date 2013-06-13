package com.duan.quotes.pack;

import java.util.LinkedList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Expressions implements Pack {
	private LinkedList<ExpressionPack> exprs = new LinkedList<ExpressionPack>();
	private LinkedList<OrderPack> orders = new LinkedList<OrderPack>();

	public Expressions add(ExpressionPack e) {
		exprs.add(e);
		return this;
	}

	public Expressions addOrder(OrderPack o) {
		orders.add(o);
		return this;
	}

	public void addToCriteria(Criteria criteria) {
		for (ExpressionPack pack : exprs)
			pack.addToCriteria(criteria);
		for (OrderPack pack : orders)
			pack.addToCriteria(criteria);
	}

	@Override
	public String toSqlString() {
		StringBuilder sb = new StringBuilder();
		int len = exprs.size();
		for (int i = 0; i < len; i++) {
			if (i == 0)
				sb.append(" WHERE ");
			else
				sb.append(" AND ");
			sb.append(exprs.get(i));
		}
		len = orders.size();
		for (int i = 0; i < len; i++) {
			if (i == 0)
				sb.append(" ORDER BY ");
			sb.append(orders.get(i));
			if (i != len - 1)
				sb.append(",");
		}
		return sb.toString();
	}
}
