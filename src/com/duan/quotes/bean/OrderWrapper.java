package com.duan.quotes.bean;

public class OrderWrapper {

	private String orderProperty;
	private boolean isAsc;

	public OrderWrapper(String orderProperty, boolean isAsc) {
		this.orderProperty = orderProperty;
		this.isAsc = isAsc;
	}

	public String getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}
}
