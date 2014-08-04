/**
 * Copyright (c) 2005-2011 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Fixtures.java 1593 2011-05-11 10:37:12Z calvinxiu $
 */
package org.hy.foundation.utils.page;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hy.foundation.utils.asserts.AssertUtils;


/**
 * 分页参数封装类.
 */
public class Page {
	//-- 分页参数 --//
	protected int pageNo = 1;
	protected int pageSize = 10;
	protected String orderBy = null;
	protected String orderDir = null;
	protected boolean countTotal = true;
	protected Integer offset;

	//-- 返回结果 --//
	protected List<?> result = null;
	protected long totalCount = -1;

	public Page() {
	}

	/**
	 * 配合RESTFUL传递参数，使用String作为构造方法参数解析至内部变量。
	 * @param pageStr <pre>2-20-nowdate,totalnum_desc,asc</pre> or <pre>2-20-</pre>
 	 */
	public Page(String pageStr) {
		String[] nums = pageStr.split("-"); // 分页与排序之间用-分隔
		if (2==nums.length) {
			this.pageNo = Integer.valueOf(nums[0]);
			this.pageSize = Integer.valueOf(nums[1]);
			if (pageStr.indexOf("_") != -1) {
				// 多个排序间用#分隔
				this.orderBy = nums[2].split("_")[0];
				this.orderDir = nums[2].split("_")[1];
			}
		}
	}

	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public Page(int pageNo, int pageSize, final String orderByStr) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		
		if (orderByStr.indexOf("_") > -1) {
			// 多个排序间用#分隔
			this.orderBy = orderByStr.split("_")[0];
			this.orderDir = orderByStr.split("_")[1];
		}
	}

	/**
	 * 获得当前页的页号, 序号从1开始, 默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号, 序号从1开始, 低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * 获得每页的记录数量, 默认为10.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量, 低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	/**
	 * 获得排序字段, 无默认值. 多个排序字段时用','分隔.
	 */
	@XmlTransient
	@JsonIgnore
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段, 多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 获得排序方向, 无默认值.
	 */
	@XmlTransient
	@JsonIgnore
	public String getOrderDir() {
		return orderDir;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param orderDir
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrderDir(final String orderDir) {
		String lowcaseOrderDir = StringUtils.lowerCase(orderDir);

		// 检查order字符串的合法值
		String[] orderDirs = StringUtils.split(lowcaseOrderDir, ',');
		for (String orderDirStr : orderDirs) {
			if (!StringUtils.equals(Sort.DESC, orderDirStr)
					&& !StringUtils.equals(Sort.ASC, orderDirStr)) {
				throw new IllegalArgumentException("排序方向" + orderDirStr
						+ "不是合法值");
			}
		}

		this.orderDir = lowcaseOrderDir;
	}

	/**
	 * 获得排序参数.
	 */
	@XmlTransient
	@JsonIgnore
	public List<Sort> getSort() {
		String[] orderBys = StringUtils.split(orderBy, ',');
		String[] orderDirs = StringUtils.split(orderDir, ',');
		AssertUtils.isTrue(orderBys.length == orderDirs.length,
				"分页多重排序参数中,排序字段与排序方向的个数不相等");

		List<Sort> orders = new ArrayList<Sort>();
		for (int i = 0; i < orderBys.length; i++) {
			orders.add(new Sort(orderBys[i], orderDirs[i]));
		}

		return orders;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	@XmlTransient
	@JsonIgnore
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils
				.isNotBlank(orderDir));
	}

	/**
	 * 是否默认计算总记录数.
	 */
	@XmlTransient
	@JsonIgnore
	public boolean isCountTotal() {
		return countTotal;
	}

	/**
	 * 设置是否默认计算总记录数.
	 */
	public void setCountTotal(boolean countTotal) {
		this.countTotal = countTotal;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置, 序号从0开始.
	 */
	public Integer getOffset() {
		return null!=offset?offset:((pageNo - 1) * pageSize);
	}
	public void setOffset(Integer offset){
		this.offset=offset;
	}

	public List<?> getResult() {
		return result;
	}

	public Page setResult(List<?> result) {
		this.result = result;
		return this;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public Page setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		return this;
	}



	public static class Sort {
		public static final String ASC = "asc";
		public static final String DESC = "desc";

		private String property;
		private String dir;

		public Sort() {
		}
		public Sort(String toString) {
			String[] strs=toString.split(",");
			this.property = strs[0];
			this.dir = strs[1];
		}
		public Sort(String property, String dir) {
			this.property = property;
			this.dir = dir;
		}

		public String getProperty() {
			return property;
		}

		public String getDir() {
			return dir;
		}
		public String toString(){
			return this.property+","+this.dir;
		}
	}
}
