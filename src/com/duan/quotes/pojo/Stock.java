package com.duan.quotes.pojo;

public class Stock {
	private String symbol;
	private String exchange;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String toString() {
		return this.exchange + this.symbol;
	}
}
