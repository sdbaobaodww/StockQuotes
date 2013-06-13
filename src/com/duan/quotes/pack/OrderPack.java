package com.duan.quotes.pack;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

public class OrderPack implements Pack {

	private String orderProperty;
	private boolean isAsc;

	protected OrderPack(String orderProperty, boolean isAsc) {
		this.orderProperty = orderProperty;
		this.isAsc = isAsc;
	}

	public static OrderPack asc(String orderProperty) {
		return new OrderPack(orderProperty, true);
	}

	public static OrderPack desc(String orderProperty) {
		return new OrderPack(orderProperty, false);
	}

	public String toSqlString() {
		return getPropertyName() + (isAsc ? " asc " : " desc ");
	}

	private String getPropertyName() {
		if (orderProperty.indexOf(".") != -1)
			return orderProperty.substring(orderProperty.indexOf(".") + 1);
		else
			return orderProperty;
	}

	public String toString() {
		return toSqlString();
	}

	@Override
	public void addToCriteria(Criteria criteria) {
		if (isAsc)
			criteria.addOrder(Order.asc(orderProperty));
		else
			criteria.addOrder(Order.desc(orderProperty));

	}
}
