package com.duan.quotes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.duan.quotes.dao.QuotesDao;
import com.duan.quotes.dao.StockDao;
import com.duan.quotes.pojo.QutoesPrimaryKey;
import com.duan.quotes.pojo.Stock;
import com.duan.quotes.pojo.StockQuotes;

public class DefaultDataEngine implements DataEngine {
	private static final Logger logger = LogManager
			.getLogger(DefaultDataEngine.class);
	private static final Pattern pattern = Pattern
			.compile("_([^_]+)=\"([^\"]+)\"");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	protected StockDao stockDao = new StockDao();
	protected QuotesDao quotesDao = new QuotesDao();

	@Override
	public List<String[]> getAllStock() {
		return null;
	}

	@Override
	public void loadAllQuotes() {
		List<Stock> stocks = stockDao.loadAllStock();
		for (Stock stock : stocks) {
			StockQuotes quotes = parseQuotes(loadQuotes(stock.toString()));
			if (quotes != null)
				quotesDao.save(quotes);
		}
	}

	@Override
	public String loadQuotes(String symbol) {
		URL url = null;
		HttpURLConnection connection = null;
		StringBuilder sb = new StringBuilder();
		try {
			url = new URL("http://hq.sinajs.cn/list=" + symbol);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String str;
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
			reader.close();
			logger.info("获取股票[" + symbol + "]行情数据成功!");
		} catch (MalformedURLException e) {
			logger.error("URL连接失败!", e);
		} catch (IOException e) {
			logger.error("读取数据失败!", e);
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return sb.toString();
	}

	@Override
	public StockQuotes parseQuotes(String quotes) {
		Matcher m = pattern.matcher(quotes);
		if (m.find()) {
			String symbol = m.group(1);
			String[] data = m.group(2).split(",");
			int j = 0;
			StockQuotes stockQuotes = new StockQuotes();
			stockQuotes.setName(data[j++]);
			BigDecimal open = BigDecimal.valueOf(Double.parseDouble(data[j++]));// 开盘价
			BigDecimal previousClose = BigDecimal.valueOf(Double
					.parseDouble(data[j++]));// 昨日收盘价
			BigDecimal lastTrade = BigDecimal.valueOf(Double
					.parseDouble(data[j++]));// 当前价格
			BigDecimal priceChange = lastTrade.subtract(previousClose);// 涨跌额
			BigDecimal percentChange = priceChange
					.divide(previousClose, 4, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(100)).setScale(2);// 涨跌幅
			stockQuotes.setOpen(open);
			stockQuotes.setPreviousClose(previousClose);
			stockQuotes.setLastTrade(lastTrade);
			stockQuotes.setPriceChange(priceChange);
			stockQuotes.setPercentChange(percentChange);
			BigDecimal high = BigDecimal.valueOf(Double.parseDouble(data[j++]));// 最高价
			BigDecimal low = BigDecimal.valueOf(Double.parseDouble(data[j++]));// 最低价
			BigDecimal amplitude = high.subtract(low)
					.divide(previousClose, 4, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(100)).setScale(2);// 振幅
			stockQuotes.setHigh(high);
			stockQuotes.setLow(low);
			stockQuotes.setAmplitude(amplitude);
			stockQuotes
					.setBid(BigDecimal.valueOf(Double.parseDouble(data[j++])));// 竞买价
			stockQuotes
					.setAsk(BigDecimal.valueOf(Double.parseDouble(data[j++])));// 竞卖价
			stockQuotes.setVolume(Integer.parseInt(data[j++]));// 成交量
			stockQuotes.setTurnover(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));// 成交额
			// 委买
			int bid1Size = Integer.parseInt(data[j++]);
			stockQuotes.setBid1Size(bid1Size);
			stockQuotes.setBid1(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));
			int bid2Size = Integer.parseInt(data[j++]);
			stockQuotes.setBid2Size(bid2Size);
			stockQuotes.setBid2(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));
			int bid3Size = Integer.parseInt(data[j++]);
			stockQuotes.setBid3Size(bid3Size);
			stockQuotes.setBid3(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));
			int bid4Size = Integer.parseInt(data[j++]);
			stockQuotes.setBid4Size(bid4Size);
			stockQuotes.setBid4(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));
			int bid5Size = Integer.parseInt(data[j++]);
			stockQuotes.setBid5Size(bid5Size);
			stockQuotes.setBid5(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));
			// 委卖
			int ask1Size = Integer.parseInt(data[j++]);
			stockQuotes.setAsk1Size(ask1Size);
			stockQuotes.setAsk1(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));
			int ask2Size = Integer.parseInt(data[j++]);
			stockQuotes.setAsk2Size(ask2Size);
			stockQuotes.setAsk2(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));
			int ask3Size = Integer.parseInt(data[j++]);
			stockQuotes.setAsk3Size(ask3Size);
			stockQuotes.setAsk3(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));
			int ask4Size = Integer.parseInt(data[j++]);
			stockQuotes.setAsk4Size(ask4Size);
			stockQuotes.setAsk4(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));
			int ask5Size = Integer.parseInt(data[j++]);
			stockQuotes.setAsk5Size(ask5Size);
			stockQuotes.setAsk5(BigDecimal.valueOf(Double
					.parseDouble(data[j++])));
			int commissionBid = bid1Size + bid2Size + bid3Size + bid4Size
					+ bid5Size;// 委买
			int commissionAsk = ask1Size + ask2Size + ask3Size + ask4Size
					+ ask5Size;// 委卖
			int commission = commissionBid - commissionAsk;// 委差
			BigDecimal cmmittee = percent(
					Double.valueOf(commissionBid - commissionAsk),
					Double.valueOf(commissionBid + commissionAsk));// 委比
			stockQuotes.setCommission(commission);
			stockQuotes.setCmmittee(cmmittee);
			try {
				QutoesPrimaryKey id = new QutoesPrimaryKey();
				id.setSymbol(symbol);
				id.setLastTradeDate(new Date(dateFormat.parse(data[j++])
						.getTime()));
				id.setLastTradeTime(new Time(timeFormat.parse(data[j++])
						.getTime()));
				stockQuotes.setId(id);
			} catch (ParseException e) {
				logger.error("解析时间字符串失败!", e);
			}
			logger.info("解析股票[" + symbol + "]行情数据成功!");
			return stockQuotes;
		}
		return null;
	}

	public BigDecimal percent(Double d1, Double d2) {
		if (d2 == 0.0)
			return new BigDecimal(0);
		return new BigDecimal(d1)
				.divide(new BigDecimal(d2), 4, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(100)).setScale(2);
	}

	public static void main(String[] args) {
		DefaultDataEngine engine = new DefaultDataEngine();
		engine.loadAllQuotes();
		// System.out.println(engine.loadQuotes("sh600172"));
	}
}
