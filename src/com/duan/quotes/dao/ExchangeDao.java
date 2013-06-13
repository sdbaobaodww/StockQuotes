package com.duan.quotes.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.duan.quotes.bean.OrderWrapper;
import com.duan.quotes.pack.ExpressionPack;
import com.duan.quotes.pack.Expressions;
import com.duan.quotes.pack.OrderPack;
import com.duan.quotes.pojo.StockQuotes;

/**
 * ��������г������ݽ��в���DAO
 * 
 * @author duanwenwu
 * 
 */
public class ExchangeDao extends BaseDao {

	public List<StockQuotes> queryQuotesOrderByProperty(Date day,
			String propertyName, boolean isDesc) {
		Criteria criteria = querySession.createCriteria(StockQuotes.class);
		if (day == null || propertyName == null || "".equals(propertyName))
			throw new IllegalArgumentException();
		criteria.add(Restrictions.eq("id.lastTradeDate", day));
		if (isDesc)
			criteria.addOrder(Order.desc(propertyName));
		else
			criteria.addOrder(Order.asc(propertyName));
		return criteria.list();
	}

	/**
	 * 
	 * @Description 
	 *              ���ݲ�ѯ��������������ɸѡ�������ݡ���ѯ����keyΪ����ֵ��valueΪ�������飬���鳤��Ϊ1������ֵ������Ϊ2����Χֵ
	 *              ����ѯ������ԭ���ϲ�Ӧ�������ڣ���Ʊ���룬��Ʊ���ơ�
	 * @author duanwenwu
	 * @param day
	 *            ��ѯ����
	 * @param parameters
	 *            ��ѯ����
	 * @param wrappers
	 *            �����װ���󼯺�
	 * @return
	 */
	public List<StockQuotes> queryQuotes(Date day,
			Map<String, Object[]> parameters, OrderWrapper[] wrappers) {
		Criteria criteria = querySession.createCriteria(StockQuotes.class);
		if (day != null)
			criteria.add(Restrictions.eq("id.lastTradeDate", day));
		if (parameters != null && parameters.size() != 0) {
			Set<Entry<String, Object[]>> entries = parameters.entrySet();
			for (Entry<String, Object[]> entry : entries) {
				String propertyName = entry.getKey();// ��������
				Object[] value = entry.getValue();// ���Ե�ֵ
				if (value.length == 1)
					criteria.add(Restrictions.eq(propertyName, value[0]));
				else if (value.length == 2) {
					criteria.add(Restrictions.ge(propertyName, value[0]));
					criteria.add(Restrictions.le(propertyName, value[1]));
				} else
					throw new IllegalArgumentException("������ֵ����ֻ��Ϊ1����2��!");
			}
		}
		if (wrappers != null && wrappers.length != 0) {
			for (OrderWrapper wrapper : wrappers) {
				if (wrapper.isAsc())
					criteria.addOrder(Order.asc(wrapper.getOrderProperty()));
				else
					criteria.addOrder(Order.desc(wrapper.getOrderProperty()));
			}
		}
		return criteria.list();
	}

	/**
	 * 
	 * @Description ����ĳ����������ݣ���������ѡ��
	 * @author duanwenwu
	 * @param day
	 * @param expression
	 * @return
	 */
	public List<StockQuotes> queryQuotes(Date day, Expressions expression) {
		Criteria criteria = querySession.createCriteria(StockQuotes.class);
		if (day != null)
			criteria.add(Restrictions.eq("id.lastTradeDate", day));
		expression.addToCriteria(criteria);
		return criteria.list();
	}

	/**
	 * 
	 * @Description ����ĳ��ʱ����������ݣ���������ѡ��
	 * @author duanwenwu
	 * @param startDate
	 * @param endDate
	 * @param expression
	 * @return
	 */
	public List<StockQuotes> queryQuotes(Date startDate, Date endDate,
			Expressions expression) {
		if (startDate == null || endDate == null)
			throw new IllegalArgumentException();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM (SELECT s.symbol,sq.name,sq.open,sq.previousClose,eq.lastTrade,ct.high,ct.low,ct.volume,ct.turnover,CAST((ct.high-ct.low)/sq.previousClose*100 AS decimal(8,2)) AS amplitude,eq.lastTrade-sq.previousClose AS priceChange,CAST((eq.lastTrade-sq.previousClose)/sq.previousClose*100 AS decimal(8,2)) AS percentChange");
		sb.append(" FROM (SELECT CONCAT(exchange,symbol) AS symbol FROM stock)AS s LEFT JOIN (SELECT symbol,name,open,previousClose FROM quotes WHERE lastTradeDate=?) AS sq ON s.symbol=sq.symbol");
		sb.append(" LEFT JOIN(SELECT symbol,lastTrade FROM quotes WHERE lastTradeDate=?)AS eq ON s.symbol=eq.symbol LEFT JOIN");
		sb.append(" (SELECT symbol,MAX(high) AS high,MIN(low) AS low,SUM(volume) AS volume,SUM(turnover) AS turnover FROM quotes WHERE lastTradeDate>=? AND lastTradeDate<=? GROUP BY symbol)AS ct ON s.symbol=ct.symbol) AS r");
		sb.append(expression.toSqlString());
		Query query = querySession.createSQLQuery(sb.toString());
		query.setDate(0, startDate);
		query.setDate(1, endDate);
		query.setDate(2, startDate);
		query.setDate(3, endDate);
		return query.list();
	}

	public static void main(String[] args) {
		ExchangeDao dao = new ExchangeDao();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 6);
		Date startDate = new Date(c.getTimeInMillis());
		c.set(Calendar.DAY_OF_MONTH, 7);
		Date endDate = new Date(c.getTimeInMillis());
		Expressions expression = new Expressions();
		expression.add(ExpressionPack.ge("percentChange", new BigDecimal(5)));
		expression.add(ExpressionPack.le("percentChange", new BigDecimal(10)));
		expression.addOrder(OrderPack.desc("percentChange"));
		expression.addOrder(OrderPack.asc("id.symbol"));
		System.out.println("start:" + startDate + " end:" + endDate);
		List result = dao.queryQuotes(startDate, endDate, expression);
		// List result = dao.queryQuotes(startDate, expression);
		System.out.println(result.size());
	}
}
