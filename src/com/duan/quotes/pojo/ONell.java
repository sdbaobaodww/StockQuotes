package com.duan.quotes.pojo;

import java.sql.Date;
import java.sql.Time;

public class ONell {
	private QutoesPrimaryKey id;
	private int indices;

	public QutoesPrimaryKey getId() {
		return id;
	}

	public void setId(QutoesPrimaryKey id) {
		this.id = id;
	}

	public int getIndices() {
		return indices;
	}

	public void setIndices(int indices) {
		this.indices = indices;
	}

	public String getSymbol() {
		return this.id.getSymbol();
	}

	public Date getLastTradeDate() {
		return this.id.getLastTradeDate();
	}

	public Time getLastTradeTime() {
		return this.id.getLastTradeTime();
	}

}
