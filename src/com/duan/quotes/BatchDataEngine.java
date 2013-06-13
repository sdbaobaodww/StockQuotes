package com.duan.quotes;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.duan.quotes.pojo.Stock;
import com.duan.quotes.pojo.StockQuotes;

public class BatchDataEngine extends DefaultDataEngine {
	private static final Logger logger = LogManager
			.getLogger(BatchDataEngine.class);
	private static final int BATCH_NUMBER = 10;

	@Override
	public void loadAllQuotes() {
		List<Stock> stocks = stockDao.loadAllStock();
		int len = stocks.size();
		String symbol = "";
		for (int i = 0; i < len; i++) {
			if (i != 0 && (i % BATCH_NUMBER == 0 || i == len - 1)) {
				symbol += stocks.get(i);
				List<StockQuotes> quotesList = parseBatchQuotes(loadQuotes(symbol));
				if (quotesList != null)
					quotesDao.saveList(quotesList);
				symbol = "";
			} else {
				symbol += stocks.get(i);
				symbol += ",";
			}
		}
		logger.info("获取所有股票行情数据成功！");
	}

	public List<StockQuotes> parseBatchQuotes(String quotes) {
		String[] batchDatas = quotes.split(";");
		if (batchDatas == null)
			return null;
		List<StockQuotes> quotesList = new ArrayList<StockQuotes>();
		for (String itemData : batchDatas) {
			StockQuotes sq = parseQuotes(itemData);
			if (sq != null)
				quotesList.add(sq);
		}
		return quotesList;
	}

	public static void main(String[] args) {
		BatchDataEngine engine = new BatchDataEngine();
		engine.loadAllQuotes();
		// System.out.println(engine.loadQuotes("sh600172"));
	}

}
