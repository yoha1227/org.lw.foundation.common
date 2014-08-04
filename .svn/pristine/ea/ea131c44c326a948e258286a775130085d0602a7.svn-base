package org.hy.foundation.common.dao.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hy.foundation.utils.filter.PropertyFilter;
import org.hy.foundation.utils.page.Page;


public interface Jpqlable {

	<X> List<X> find(final String jpql, final Map<String, ?> values);

	/**
	 * 按jpql查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	<X> List<X> find(final String jpql, final Map<String, ?> values,
			final Page page) ;

	/**
	 * 根据查询jpql与参数列表创建Query对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	Query createQuery(final String queryString,
			final Map<String, ?> values) ;
	/**
	 * 根据查询jpql与参数列表创建Query对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	Query createQuery(String queryString,
			final Map<String, ?> values, final Page page);

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	long countJpqlResult(final String jpql, final Map<String, ?> values);

	/**
	 * 按属性条件列表创建Jpql语句,辅助函数.
	 * 
	 * @param jpql 传入的语句必须是完整包含where的，即只能以where a=x 结尾。
	 */
	String buildJpqlByPropertyFilter(final String jqpl,
			final List<PropertyFilter> filters);

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	String buildJpql(final PropertyFilter filter);
}
