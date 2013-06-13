package com.duan.quotes.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.duan.quotes.bean.QuotesQuery;
import com.duan.quotes.pojo.StockQuotes;

/**
 * 针对单个股票进行操作DAO
 * 
 * @author duanwenwu
 * 
 */
public class QuotesDao extends BaseDao {

	public StockQuotes loadQuotes(String symbol, Date date) {
		Query query = querySession
				.createQuery("from StockQuotes where symbol=:symbol and lastTradeDate=:date");
		query.setString("symbol", symbol);
		query.setDate("date", date);
		return (StockQuotes) query.uniqueResult();
	}

	/**
	 * 
	 * @Description 查询股票单日行情数据
	 * @author duanwenwu
	 * @param symbol
	 * @param singleDate
	 * @return
	 */
	public List<StockQuotes> queryQuotes(String symbol, Date singleDate) {
		Criteria criteria = querySession.createCriteria(StockQuotes.class);
		if (symbol == null || singleDate == null)
			throw new IllegalArgumentException();
		criteria.add(Restrictions.eq("id.symbol", symbol));
		criteria.add(Restrictions.eq("id.lastTradeDate", singleDate));
		return criteria.list();
	}

	/**
	 * 
	 * @Description 查询股票某个时间范围内行情数据集合
	 * @author duanwenwu
	 * @param symbol
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<StockQuotes> queryQuotes(String symbol, Date startDate,
			Date endDate) {
		Criteria criteria = querySession.createCriteria(StockQuotes.class);
		if (symbol == null)
			throw new IllegalArgumentException();
		criteria.add(Restrictions.eq("id.symbol", symbol));
		if (startDate != null)
			criteria.add(Restrictions.ge("id.lastTradeDate", startDate));
		if (endDate != null)
			criteria.add(Restrictions.le("id.lastTradeDate", endDate));
		return criteria.list();
	}

	/**
	 * 
	 * @Description 查询一段时间内股票汇总行情
	 * @author duanwenwu
	 * @param symbol
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<StockQuotes> queryCountedQuotes(String symbol, Date startDate,
			Date endDate) {
		if (symbol == null || startDate == null || endDate == null)
			throw new IllegalArgumentException();
		Query query = querySession
				.getNamedQuery("queryQuotesBySymbolAndDateRange");
		query.setString(1, symbol);
		query.setDate(2, startDate);
		query.setDate(3, endDate);
		return query.list();
	}

	/**
	 * 根据属性对行情数据进行排序
	 * 
	 * @param condition
	 * @param propertyName
	 * @param isDesc
	 * @return
	 */
	public List<StockQuotes> queryQuotesOrderByProperty(QuotesQuery condition,
			String propertyName, boolean isDesc) {
		Criteria criteria = querySession.createCriteria(StockQuotes.class);
		if (condition == null || propertyName == null
				|| "".equals(propertyName))
			throw new IllegalArgumentException();
		if (condition.getSymbol() != null)
			criteria.add(Restrictions.eq("id.symbol", condition.getSymbol()));
		if (condition.getSingleDate() != null) {// 单日查询
			criteria.add(Restrictions.eq("id.lastTradeDate",
					condition.getSingleDate()));
		} else {// 一段日期范围查询
			if (condition.getStartDate() != null)
				criteria.add(Restrictions.ge("id.lastTradeDate",
						condition.getStartDate()));
			if (condition.getEndDate() != null)
				criteria.add(Restrictions.le("id.lastTradeDate",
						condition.getEndDate()));
		}
		if (isDesc)
			criteria.addOrder(Order.desc(propertyName));
		else
			criteria.addOrder(Order.asc(propertyName));
		return criteria.list();
	}

	/**
	 * 
	 * @Description 根据某个属性值范围(闭区间)查询行情数据
	 * @author duanwenwu
	 * @param condition
	 * @param propertyName
	 * @param value1
	 *            起始值
	 * @param value2
	 *            结束值
	 * @return
	 */
	public List<StockQuotes> queryQuotesByPropertyRange(QuotesQuery condition,
			String propertyName, Object value1, Object value2) {
		Criteria criteria = querySession.createCriteria(StockQuotes.class);
		if (condition == null || propertyName == null
				|| "".equals(propertyName) || value1 == null || value2 == null)
			throw new IllegalArgumentException();
		if (condition.getSymbol() != null)
			criteria.add(Restrictions.eq("id.symbol", condition.getSymbol()));
		if (condition.getSingleDate() != null) {// 单日查询
			criteria.add(Restrictions.eq("id.lastTradeDate",
					condition.getSingleDate()));
		} else {// 一段日期范围查询
			if (condition.getStartDate() != null)
				criteria.add(Restrictions.ge("id.lastTradeDate",
						condition.getStartDate()));
			if (condition.getEndDate() != null)
				criteria.add(Restrictions.le("id.lastTradeDate",
						condition.getEndDate()));
		}
		criteria.add(Restrictions.ge(propertyName, value1));
		criteria.add(Restrictions.le(propertyName, value2));
		return criteria.list();
	}

	/**
	 * 
	 * @Description 根据某个属性值查询行情数据
	 * @author duanwenwu
	 * @param condition
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<StockQuotes> queryQuotesByPropertyValue(QuotesQuery condition,
			String propertyName, Object value) {
		Criteria criteria = querySession.createCriteria(StockQuotes.class);
		if (condition == null || propertyName == null
				|| "".equals(propertyName) || value == null)
			throw new IllegalArgumentException();
		if (condition.getSymbol() != null)
			criteria.add(Restrictions.eq("id.symbol", condition.getSymbol()));
		if (condition.getSingleDate() != null) {// 单日查询
			criteria.add(Restrictions.eq("id.lastTradeDate",
					condition.getSingleDate()));
		} else {// 一段日期范围查询
			if (condition.getStartDate() != null)
				criteria.add(Restrictions.ge("id.lastTradeDate",
						condition.getStartDate()));
			if (condition.getEndDate() != null)
				criteria.add(Restrictions.le("id.lastTradeDate",
						condition.getEndDate()));
		}
		criteria.add(Restrictions.eq(propertyName, value));
		return criteria.list();
	}

	/**
	 * 
	 * @Description 根据某个属性值查询行情数据,获取数据集合大小
	 * @author duanwenwu
	 * @param condition
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public int queryCountByProertyValue(QuotesQuery condition,
			String propertyName, Object value) {
		Criteria criteria = querySession.createCriteria(StockQuotes.class);
		if (condition == null || propertyName == null
				|| "".equals(propertyName) || value == null)
			throw new IllegalArgumentException();
		if (condition.getSymbol() != null)
			criteria.add(Restrictions.eq("id.symbol", condition.getSymbol()));
		if (condition.getSingleDate() != null) {// 单日查询
			criteria.add(Restrictions.eq("id.lastTradeDate",
					condition.getSingleDate()));
		} else {// 一段日期范围查询
			if (condition.getStartDate() != null)
				criteria.add(Restrictions.ge("id.lastTradeDate",
						condition.getStartDate()));
			if (condition.getEndDate() != null)
				criteria.add(Restrictions.le("id.lastTradeDate",
						condition.getEndDate()));
		}
		criteria.add(Restrictions.eq(propertyName, value));
		criteria.setProjection(Projections.count("id.symbol"));
		return Integer.parseInt(criteria.list().get(0).toString());
	}

	public static void main(String[] args) {
		QuotesDao dao = new QuotesDao();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -2);
		// List list = dao.queryAndOrderQuotesProperty(
		// new Date(c.getTimeInMillis()), "percentChange", true);
		List list = dao
				.queryQuotesByPropertyRange(QuotesQuery.getQuotesQuery(null,
						new Date(c.getTimeInMillis())), "percentChange",
						new BigDecimal(9.0), new BigDecimal(11.0));
		System.out.println(list.size());
	}
}
