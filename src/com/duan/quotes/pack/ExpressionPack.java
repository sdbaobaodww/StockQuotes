package com.duan.quotes.pack;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ExpressionPack implements Pack {
	private final String propertyName;
	private final Object value;
	private final String op;

	protected ExpressionPack(String propertyName, Object value, String op) {
		this.propertyName = propertyName;
		this.value = value;
		this.op = op;
	}

	public static ExpressionPack eq(String propertyName, Object value) {
		return new ExpressionPack(propertyName, value, "=");
	}

	public static ExpressionPack ne(String propertyName, Object value) {
		return new ExpressionPack(propertyName, value, "<>");
	}

	public static ExpressionPack lt(String propertyName, Object value) {
		return new ExpressionPack(propertyName, value, "<");
	}

	public static ExpressionPack gt(String propertyName, Object value) {
		return new ExpressionPack(propertyName, value, ">");
	}

	public static ExpressionPack le(String propertyName, Object value) {
		return new ExpressionPack(propertyName, value, "<=");
	}

	public static ExpressionPack ge(String propertyName, Object value) {
		return new ExpressionPack(propertyName, value, ">=");
	}

	public String toSqlString() {
		return getPropertyName() + this.op
				+ (isNumber(value) ? value : "'" + value + "'");
	}

	private String getPropertyName() {
		if (propertyName.indexOf(".") != -1)
			return propertyName.substring(propertyName.indexOf(".") + 1);
		else
			return propertyName;
	}

	public String toString() {
		return toSqlString();
	}

	private boolean isNumber(Object value) {
		if (Number.class.isAssignableFrom(value.getClass()))
			return true;
		return false;
	}

	@Override
	public void addToCriteria(Criteria criteria) {
		if ("==".equals(op))
			criteria.add(Restrictions.eq(propertyName, value));
		else if ("<>".equals(op))
			criteria.add(Restrictions.ne(propertyName, value));
		else if ("<".equals(op))
			criteria.add(Restrictions.lt(propertyName, value));
		else if (">".equals(op))
			criteria.add(Restrictions.gt(propertyName, value));
		else if ("<=".equals(op))
			criteria.add(Restrictions.le(propertyName, value));
		else if (">=".equals(op))
			criteria.add(Restrictions.ge(propertyName, value));

	}
}
