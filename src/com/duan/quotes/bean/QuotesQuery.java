package com.duan.quotes.bean;

import java.sql.Date;

import com.duan.quotes.common.TimeUtils;

public class QuotesQuery {

	private String symbol;// ��Ʊ����
	private Date startDate;// ������ʼʱ��
	private Date endDate;// �������ʱ��
	private Date singleDate;// ���������ѯ

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
	 * ���ɵ��죬���ܣ����£�����������ѯ����
	 * 
	 * @param symbol
	 *            ��Ʊ����
	 * @param mode
	 *            ģʽ�����죬���ܣ����£������ĸ�ֵ
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
	 * ���ɵ���������ѯ����
	 * 
	 * @param symbol
	 *            ��Ʊ����
	 * @return
	 */
	public static QuotesQuery getTodayQuotesQuery(String symbol) {
		return getQuotesQuery(symbol, StockCycle.CYCLE_TODAY);
	}

	/**
	 * ���ɵ��������ѯ����
	 * 
	 * @param symbol
	 *            ��Ʊ����
	 * @param singleDay
	 *            ��������
	 * @return
	 */
	public static QuotesQuery getQuotesQuery(String symbol, Date singleDay) {
		return new QuotesQuery(symbol, singleDay);
	}

	/**
	 * ����ĳ��ʱ�䷶Χ�������ѯ����
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
