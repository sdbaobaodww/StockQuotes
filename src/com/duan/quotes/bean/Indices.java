package com.duan.quotes.bean;

import com.duan.quotes.pojo.StockQuotes;

public class Indices {
	private StockQuotes quotes;
	private String indicesValue;

	public StockQuotes getQuotes() {
		return quotes;
	}

	public void setQuotes(StockQuotes quotes) {
		this.quotes = quotes;
	}

	public String getIndicesValue() {
		return indicesValue;
	}

	public void setIndicesValue(String indicesValue) {
		this.indicesValue = indicesValue;
	}
}
