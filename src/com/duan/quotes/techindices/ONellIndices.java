package com.duan.quotes.techindices;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.duan.quotes.bean.QuotesQuery;
import com.duan.quotes.dao.QuotesDao;
import com.duan.quotes.pojo.ONell;
import com.duan.quotes.pojo.StockQuotes;

/**
 * 欧奈尔股票相对强度指标
 * 
 * @author duanwenwu
 * 
 */
public class ONellIndices implements TechIndices {
	private QuotesDao dao = new QuotesDao();
	private static final Logger logger = LogManager
			.getLogger(ONellIndices.class);

	public List evaluateAll(QuotesQuery condition) {
		List orderedQuotes = dao.queryQuotesOrderByProperty(condition,
				"percentChange", true);
		int haltSize = dao.queryCountByProertyValue(condition, "open",
				new BigDecimal(0));
		int len = orderedQuotes.size();
		int validSize = len - haltSize;
		int mode = (int) Math.ceil(validSize / 100.0);
		logger.info("停牌数:" + haltSize + " 正常交易数:" + validSize + " 步长:" + mode);
		List oNells = new ArrayList();
		for (int i = 0; i < len; i++) {
			ONell indices = new ONell();
			indices.setId(((StockQuotes) orderedQuotes.get(i)).getId());
			if (i < validSize)
				indices.setIndices(99 - (i / mode));
			else
				indices.setIndices(0);
			oNells.add(indices);
		}
		return oNells;
	}

	public void evaluateIndices() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		List values = evaluateAll(QuotesQuery.getQuotesQuery(null,
				new Date(c.getTimeInMillis())));
		dao.saveList(values);
		logger.info("保存欧奈尔股票相对强度指标值成功！");
	}

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -2);
		ONellIndices onell = new ONellIndices();
		List<ONell> indices = onell.evaluateAll(QuotesQuery.getQuotesQuery(
				null, new Date(c.getTimeInMillis())));
		for (ONell ix : indices) {
			System.out.println(ix.getSymbol() + ":" + ix.getIndices());
		}
	}

}
