package com.duan.quotes.pojo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class StockQuotes {
	private QutoesPrimaryKey id;
	private String name;
	private BigDecimal open;// 开盘价
	private BigDecimal previousClose;// 昨日收盘价
	private BigDecimal lastTrade;// 当前价格
	private BigDecimal priceChange;// 涨跌额（元）
	private BigDecimal percentChange;// 涨跌幅
	private BigDecimal high;// 最高价
	private BigDecimal low;// 最低价
	private BigDecimal amplitude;// 振幅
	private BigDecimal bid;// 竞买价
	private BigDecimal ask;// 竞卖价
	private int volume;// 成交量
	private BigDecimal turnover;// 成交额(元)
	private int bid1Size;// 买一规模(股)
	private BigDecimal bid1;// 买一报价
	private int bid2Size;// 买二规模(股)
	private BigDecimal bid2;// 买二报价
	private int bid3Size;// 买三规模(股)
	private BigDecimal bid3;// 买三报价
	private int bid4Size;// 买四规模(股)
	private BigDecimal bid4;// 买四报价
	private int bid5Size;// 买五规模(股)
	private BigDecimal bid5;// 买五报价

	private int ask1Size;// 卖一规模(股)
	private BigDecimal ask1;// 卖一报价
	private int ask2Size;// 卖二规模(股)
	private BigDecimal ask2;// 卖二报价
	private int ask3Size;// 卖三规模(股)
	private BigDecimal ask3;// 卖三报价
	private int ask4Size;// 卖四规模(股)
	private BigDecimal ask4;// 卖四报价
	private int ask5Size;// 卖五规模(股)
	private BigDecimal ask5;// 卖五报价

	private int commission;// 委差
	private BigDecimal cmmittee;// 委比

	public QutoesPrimaryKey getId() {
		return id;
	}

	public void setId(QutoesPrimaryKey id) {
		this.id = id;
	}

	public String getSymbol() {
		return this.id.getSymbol();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(BigDecimal previousClose) {
		this.previousClose = previousClose;
	}

	public BigDecimal getLastTrade() {
		return lastTrade;
	}

	public void setLastTrade(BigDecimal lastTrade) {
		this.lastTrade = lastTrade;
	}

	public BigDecimal getPriceChange() {
		return priceChange;
	}

	public void setPriceChange(BigDecimal priceChange) {
		this.priceChange = priceChange;
	}

	public BigDecimal getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(BigDecimal percentChange) {
		this.percentChange = percentChange;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(BigDecimal amplitude) {
		this.amplitude = amplitude;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public BigDecimal getAsk() {
		return ask;
	}

	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public int getBid1Size() {
		return bid1Size;
	}

	public void setBid1Size(int bid1Size) {
		this.bid1Size = bid1Size;
	}

	public BigDecimal getBid1() {
		return bid1;
	}

	public void setBid1(BigDecimal bid1) {
		this.bid1 = bid1;
	}

	public int getBid2Size() {
		return bid2Size;
	}

	public void setBid2Size(int bid2Size) {
		this.bid2Size = bid2Size;
	}

	public BigDecimal getBid2() {
		return bid2;
	}

	public void setBid2(BigDecimal bid2) {
		this.bid2 = bid2;
	}

	public int getBid3Size() {
		return bid3Size;
	}

	public void setBid3Size(int bid3Size) {
		this.bid3Size = bid3Size;
	}

	public BigDecimal getBid3() {
		return bid3;
	}

	public void setBid3(BigDecimal bid3) {
		this.bid3 = bid3;
	}

	public int getBid4Size() {
		return bid4Size;
	}

	public void setBid4Size(int bid4Size) {
		this.bid4Size = bid4Size;
	}

	public BigDecimal getBid4() {
		return bid4;
	}

	public void setBid4(BigDecimal bid4) {
		this.bid4 = bid4;
	}

	public int getBid5Size() {
		return bid5Size;
	}

	public void setBid5Size(int bid5Size) {
		this.bid5Size = bid5Size;
	}

	public BigDecimal getBid5() {
		return bid5;
	}

	public void setBid5(BigDecimal bid5) {
		this.bid5 = bid5;
	}

	public int getAsk1Size() {
		return ask1Size;
	}

	public void setAsk1Size(int ask1Size) {
		this.ask1Size = ask1Size;
	}

	public BigDecimal getAsk1() {
		return ask1;
	}

	public void setAsk1(BigDecimal ask1) {
		this.ask1 = ask1;
	}

	public int getAsk2Size() {
		return ask2Size;
	}

	public void setAsk2Size(int ask2Size) {
		this.ask2Size = ask2Size;
	}

	public BigDecimal getAsk2() {
		return ask2;
	}

	public void setAsk2(BigDecimal ask2) {
		this.ask2 = ask2;
	}

	public int getAsk3Size() {
		return ask3Size;
	}

	public void setAsk3Size(int ask3Size) {
		this.ask3Size = ask3Size;
	}

	public BigDecimal getAsk3() {
		return ask3;
	}

	public void setAsk3(BigDecimal ask3) {
		this.ask3 = ask3;
	}

	public int getAsk4Size() {
		return ask4Size;
	}

	public void setAsk4Size(int ask4Size) {
		this.ask4Size = ask4Size;
	}

	public BigDecimal getAsk4() {
		return ask4;
	}

	public void setAsk4(BigDecimal ask4) {
		this.ask4 = ask4;
	}

	public int getAsk5Size() {
		return ask5Size;
	}

	public void setAsk5Size(int ask5Size) {
		this.ask5Size = ask5Size;
	}

	public BigDecimal getAsk5() {
		return ask5;
	}

	public void setAsk5(BigDecimal ask5) {
		this.ask5 = ask5;
	}

	public int getCommission() {
		return commission;
	}

	public void setCommission(int commission) {
		this.commission = commission;
	}

	public BigDecimal getCmmittee() {
		return cmmittee;
	}

	public void setCmmittee(BigDecimal cmmittee) {
		this.cmmittee = cmmittee;
	}

	public Date getLastTradeDate() {
		return this.id.getLastTradeDate();
	}

	public Time getLastTradeTime() {
		return this.id.getLastTradeTime();
	}

}
