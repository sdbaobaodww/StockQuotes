package com.duan.quotes.pojo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class QutoesPrimaryKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private String symbol;
	private Date lastTradeDate;
	private Time lastTradeTime;

	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof QutoesPrimaryKey))
			return false;
		QutoesPrimaryKey otherKey = (QutoesPrimaryKey) other;
		if (!symbol.equalsIgnoreCase(otherKey.getSymbol()))
			return false;
		if (lastTradeDate.compareTo(otherKey.getLastTradeDate()) != 0)
			return false;
		return true;
	}

	public int hashCode() {
		int result = 17;
		result = 31 * result + symbol.hashCode();
		result = 31 * result + lastTradeDate.hashCode();
		return result;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getLastTradeDate() {
		return lastTradeDate;
	}

	public void setLastTradeDate(Date lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}

	public Time getLastTradeTime() {
		return lastTradeTime;
	}

	public void setLastTradeTime(Time lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

}
