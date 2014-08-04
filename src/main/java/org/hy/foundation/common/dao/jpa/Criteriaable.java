package org.hy.foundation.common.dao.jpa;

import java.util.List;

import org.criteria4jpa.Criteria;
import org.criteria4jpa.criterion.Criterion;
import org.hy.foundation.utils.filter.PropertyFilter;
import org.hy.foundation.utils.page.Page;
import org.hy.foundation.utils.page.Page.Sort;


public interface Criteriaable {

	Criteria createCriteria(final Criterion... criterions);

	Criteria createCriteria(final List<PropertyFilter> filters);

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	Criterion buildCriterion(final PropertyFilter propertyFilter);

	/**
	 * 按属性条件列表创建Criterion数组,辅助函数.
	 */
	Criterion[] buildCriterionByPropertyFilter(
			final List<PropertyFilter> filters);

	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	Criteria setPageToCriteria(final Criteria c, final Page page);

	/**
	 * 设置排序参数到Criteria对象,辅助函数.单独列出是因为有些情况只需要排序 ，不需要分页
	 */
	Criteria setSortToCriteria(final Criteria c, final List<Sort> sorts);
}
