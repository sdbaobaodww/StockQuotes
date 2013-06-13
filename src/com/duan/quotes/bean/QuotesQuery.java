package com.duan.quotes.bean;

import java.sql.Date;

import com.duan.quotes.common.TimeUtils;

public class QuotesQuery {

	private String symbol;// 股票代码
	private Date startDate;// 行情起始时间
	private Date endDate;// 行情结束时间
	private Date singleDate;// 单日行情查询

	private QuotesQuery(String symbol) {
		this(symbol, null, null);
	}

	private QuotesQuery(String symbol, Date startDate, Date endDate) {
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	private QuotesQuery(String symbol, Date singleDate) {
		this.symbol = symbol;
		this.singleDate = singleDate;
	}

	/**
	 * 生成当天，当周，当月，当年的行情查询对象
	 * 
	 * @param symbol
	 *            股票代码
	 * @param mode
	 *            模式，当天，当周，当月，当年四个值
	 * @return
	 */
	public static QuotesQuery getQuotesQuery(String symbol, StockCycle mode) {
		QuotesQuery query = new QuotesQuery(symbol);
		if (StockCycle.CYCLE_TODAY == mode)
			query.setSingleDate(TimeUtils.getToday());
		else {
			query.setEndDate(TimeUtils.getToday());
			if (StockCycle.CYCLE_CUR_WEEK == mode)
				query.setStartDate(TimeUtils.getStartDayOfWeek());
			else if (StockCycle.CYCLE_CUR_MONTH == mode)
				query.setStartDate(TimeUtils.getStartDayOfMonth());
			else if (StockCycle.CYCLE_CUR_YEAR == mode)
				query.setStartDate(TimeUtils.getStartDayOfYear());
		}
		return query;
	}

	/**
	 * 生成当天的行情查询对象
	 * 
	 * @param symbol
	 *            股票代码
	 * @return
	 */
	public static QuotesQuery getTodayQuotesQuery(String symbol) {
		return getQuotesQuery(symbol, StockCycle.CYCLE_TODAY);
	}

	/**
	 * 生成单天行情查询对象
	 * 
	 * @param symbol
	 *            股票代码
	 * @param singleDay
	 *            单日日期
	 * @return
	 */
	public static QuotesQuery getQuotesQuery(String symbol, Date singleDay) {
		return new QuotesQuery(symbol, singleDay);
	}

	/**
	 * 生成某段时间范围的行情查询对象
	 * 
	 * @param symbol
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static QuotesQuery getQuotesQuery(String symbol, Date startDate,
			Date endDate) {
		return new QuotesQuery(symbol, startDate, endDate);
	}

	public String getSymbol() {
		return symbol;
	}

	void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getStartDate() {
		return startDate;
	}

	void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getSingleDate() {
		return singleDate;
	}

	void setSingleDate(Date singleDate) {
		this.singleDate = singleDate;
	}

}
