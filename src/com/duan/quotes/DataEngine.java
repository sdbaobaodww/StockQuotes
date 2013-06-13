package com.duan.quotes;

import java.util.List;

import com.duan.quotes.pojo.StockQuotes;

/**
 * 通过网络获取股票数据接口
 * 
 * @author duanwenwu
 * 
 */
public interface DataEngine {

	/**
	 * 获取所有股票信息
	 * 
	 * @return 股票信息集合，String[0]股票代码;String[1]交易所代码
	 */
	public List<String[]> getAllStock();

	/**
	 * 加载所有股票的行情数据
	 */
	public void loadAllQuotes();

	/**
	 * 加载特定股票代码的行情数据
	 * 
	 * @param symbol
	 *            单个或者多个股票代码，多个使用","分割
	 * @return 股票行情数据
	 */
	public String loadQuotes(String symbol);

	/**
	 * 解析行情数据，获取股票行情对象
	 * 
	 * @param quotes
	 *            行情数据
	 * @return
	 */
	public StockQuotes parseQuotes(String quotes);

}
