package com.duan.quotes;

import java.util.List;

import com.duan.quotes.pojo.StockQuotes;

/**
 * ͨ�������ȡ��Ʊ���ݽӿ�
 * 
 * @author duanwenwu
 * 
 */
public interface DataEngine {

	/**
	 * ��ȡ���й�Ʊ��Ϣ
	 * 
	 * @return ��Ʊ��Ϣ���ϣ�String[0]��Ʊ����;String[1]����������
	 */
	public List<String[]> getAllStock();

	/**
	 * �������й�Ʊ����������
	 */
	public void loadAllQuotes();

	/**
	 * �����ض���Ʊ�������������
	 * 
	 * @param symbol
	 *            �������߶����Ʊ���룬���ʹ��","�ָ�
	 * @return ��Ʊ��������
	 */
	public String loadQuotes(String symbol);

	/**
	 * �����������ݣ���ȡ��Ʊ�������
	 * 
	 * @param quotes
	 *            ��������
	 * @return
	 */
	public StockQuotes parseQuotes(String quotes);

}
