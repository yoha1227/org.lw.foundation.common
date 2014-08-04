package org.hy.foundation.common.dao.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.criteria4jpa.Criteria;
import org.criteria4jpa.Criteria.JoinType;
import org.criteria4jpa.CriteriaUtils;
import org.criteria4jpa.criterion.Criterion;
import org.criteria4jpa.criterion.MatchMode;
import org.criteria4jpa.criterion.Restrictions;
import org.criteria4jpa.order.Order;
import org.hy.foundation.common.entity.jpa.BasicEntity;
import org.hy.foundation.utils.asserts.AssertUtils;
import org.hy.foundation.utils.filter.PropertyFilter;
import org.hy.foundation.utils.page.Page;
import org.hy.foundation.utils.page.Page.Sort;

public class CrudableDaoUtils<T extends BasicEntity> {

	/** 查询参数属性比较类型. */
	public enum MatchType {
		IN, EQ, LIKE, LT, GT, ISNULL, LE, GE, JPQL;
	}

	protected EntityManager entityManager = null;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName="CrudableBaseJPA")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public CrudableDaoUtils() {
		super();
	}

	/**
	 * 查询sql语句
	 * @param sql
	 * @return
	 */
	protected List findSql(String sql) {
		return entityManager.createNativeQuery(sql).getResultList();
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public Criteria createCriteria(Class<T> entityClass, final Criterion... criterions) {
		Criteria criteria = CriteriaUtils.createCriteria(entityManager, entityClass);
		if (criterions != null) {
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}
	
		return criteria;
	}

	public Criteria createCriteria(Class<T> entityClass, final List<PropertyFilter> filters) {
		// 根查询节点
		Criteria criteria = CriteriaUtils.createCriteria(entityManager, entityClass);
		if (filters == null) {
			return criteria;
		}
	
		// 所有查询节点 （key：名字，value：查询）
		Map<String, Criteria> allCriterias = new HashMap<String, Criteria>();
		allCriterias.put("", criteria);
	
		for (PropertyFilter filter : filters) {
			// 检查Map中查询节点是否构建
			String[] criteriaNames = filter.getAssociateName().split("\\.");
			for (int i = 0; i < criteriaNames.length; i++) {
				// 父查询节点
				String parentKey = StringUtils.join(
						ArrayUtils.subarray(criteriaNames, 0, i), ".");
				// 自身查询节点
				String selfKey = StringUtils.join(
						ArrayUtils.subarray(criteriaNames, 0, i + 1), ".");
				if (!allCriterias.containsKey(selfKey)) {
					allCriterias
							.put(selfKey,
									allCriterias.get(parentKey).createCriteria(
											criteriaNames[i],
											JoinType.LEFT_OUTER_JOIN));
				}
			}
	
			allCriterias.get(filter.getAssociateName()).add(
					buildCriterion(filter));
		}
		return criteria;
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	public Criterion buildCriterion(final PropertyFilter propertyFilter) {
		String propertyName = propertyFilter.getPropertyName();
		Object propertyValue = propertyFilter.getPropertyValue();
		MatchType matchType = propertyFilter.getMatchType();
	
		AssertUtils.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = null;
		// 根据MatchType构造criterion
		switch (matchType) {
		case IN:
			criterion = Restrictions.in(propertyName,
					(Collection<?>) propertyValue);
			break;
		case EQ:
			criterion = Restrictions.eq(propertyName, propertyValue);
			break;
		case LIKE:
			criterion = Restrictions.like(propertyName, (String) propertyValue,
					MatchMode.ANYWHERE);
			break;
		case LE:
			criterion = Restrictions.le(propertyName, propertyValue);
			break;
		case LT:
			criterion = Restrictions.lt(propertyName, propertyValue);
			break;
		case GE:
			criterion = Restrictions.ge(propertyName, propertyValue);
			break;
		case GT:
			criterion = Restrictions.gt(propertyName, propertyValue);
			break;
		case ISNULL:
			criterion = Restrictions.isNull(propertyName);
			break;
		case JPQL: // JPQL无参数
			criterion = Restrictions.jpqlRestriction(propertyName,
					new Object[] {});
		}
		return criterion;
	}

	/**
	 * 按属性条件列表创建Criterion数组,辅助函数.
	 */
	public Criterion[] buildCriterionByPropertyFilter(final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
			criterionList.add(buildCriterion(filter));
		}
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}

	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	public Criteria setPageToCriteria(final Criteria c, final Page page) {
		if(page==null) return c;
		
		AssertUtils.isTrue(page.getPageSize() > 0,
				"Page Size must larger than zero");
	
		c.setFirstResult(page.getOffset());
		c.setMaxResults(page.getPageSize());
	
		if (page.isOrderBySetted()) {
			setSortToCriteria(c, page.getSort());
		}
	
		return c;
	}

	/**
	 * 设置排序参数到Criteria对象,辅助函数.单独列出是因为有些情况只需要排序 ，不需要分页
	 */
	public Criteria setSortToCriteria(final Criteria c, final List<Sort> sorts) {
		if(sorts==null) return c;
		
		for (Sort sort : sorts) {
			if (Sort.ASC.equals(sort.getDir())) {
				c.addOrder(Order.asc(sort.getProperty()));
			} else {
				c.addOrder(Order.desc(sort.getProperty()));
			}
		}
		return c;
	}

	/**
	 * 按JPQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> List<X> find(final String JPQL, final Map<String, ?> values) {
		return createQuery(JPQL, values).getResultList();
	}

	/**
	 * 按JPQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> List<X> find(final String JPQL, final Map<String, ?> values, final Page page) {
		return createQuery(JPQL, values, page).getResultList();
	}

	/**
	 * 根据查询JPQL与参数列表创建Query对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public Query createQuery(final String queryString, final Map<String, ?> values) {
		return createQuery(queryString, values, null);
	}

	/**
	 * 根据查询JPQL与参数列表创建Query对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public Query createQuery(String queryString, final Map<String, ?> values, final Page page) {
		AssertUtils.hasText(queryString, "queryString不能为空");
	
		if (page != null && page.isOrderBySetted()) {
			StringBuffer strBuf = new StringBuffer(queryString);
			strBuf.append(" order by ");
			for (Sort sort : page.getSort()) {
				if (Sort.ASC.equals(sort.getDir())) {
					strBuf.append(sort.getProperty() + " asc,");
				} else {
					strBuf.append(sort.getProperty() + " desc,");
				}
			}
			strBuf.deleteCharAt(strBuf.length() - 1);
			queryString = strBuf.toString();
		}
	
		Query query = entityManager.createQuery(queryString);
		if (values != null) {
			for (String key : values.keySet()) {
				query.setParameter(key, values.get(key));
			}
		}
		if (page != null) {
			query.setFirstResult(page.getOffset());
			query.setMaxResults(page.getPageSize());
		}
	
		return query;
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	public long countJpqlResult(final String jpql, final Map<String, ?> values) {
		String countJpql = createCountJpql(jpql);
	
		try {
			Long count = (Long) createQuery(countJpql, values)
					.getSingleResult();
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countJpql, e);
		}
	}

	/**
	 * 按属性条件列表创建Jpql语句,辅助函数.
	 * 
	 * @param jpql
	 *            传入的语句必须是完整包含where的，即只能以where a=x 结尾。
	 */
	public String buildJpqlByPropertyFilter(final String jqpl, final List<PropertyFilter> filters) {
		StringBuffer jpql = new StringBuffer(jqpl);
		for (PropertyFilter filter : filters) {
			jpql.append(" and ");
			jpql.append(buildJpql(filter));
		}
		return jpql.toString();
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	public String buildJpql(final PropertyFilter filter) {
		String propertyName = filter.getPropertyName();
		MatchType matchType = filter.getMatchType();
		AssertUtils.hasText(propertyName, "propertyName不能为空");
	
		StringBuffer jpql = new StringBuffer();
	
		// 根据MatchType构造JPQL
		switch (matchType) {
		case IN:
			jpql.append(" ");
			jpql.append(propertyName);
			jpql.append(" in  (:");
			jpql.append(propertyName.replaceAll("\\.", "_"));
			jpql.append(") ");
			break;
		case EQ:
			jpql.append(" ");
			jpql.append(propertyName);
			jpql.append(" = :");
			jpql.append(propertyName.replaceAll("\\.", "_"));
			jpql.append(" ");
			break;
		case LIKE:
			jpql.append(" ");
			jpql.append(propertyName);
			jpql.append(" like :");
			jpql.append(propertyName.replaceAll("\\.", "_"));
			jpql.append(" ");
			break;
		case LE:
			jpql.append(" ");
			jpql.append(propertyName);
			jpql.append(" <= :");
			jpql.append(propertyName.replaceAll("\\.", "_"));
			jpql.append(" ");
			break;
		case LT:
			jpql.append(" ");
			jpql.append(propertyName);
			jpql.append(" < :");
			jpql.append(propertyName.replaceAll("\\.", "_"));
			jpql.append(" ");
			break;
		case GE:
			jpql.append(" ");
			jpql.append(propertyName);
			jpql.append(" > :");
			jpql.append(propertyName.replaceAll("\\.", "_"));
			jpql.append(" ");
			break;
		case GT:
			jpql.append(" ");
			jpql.append(propertyName);
			jpql.append(" >= :");
			jpql.append(propertyName.replaceAll("\\.", "_"));
			jpql.append(" ");
			break;
		case JPQL:
			jpql.append(" ");
			jpql.append(propertyName);
			jpql.append(" ");
		}
		return jpql.toString();
	}

	private String createCountJpql(String orgJpql) {
		String countJpql = "select count (*) "
				+ removeSelect(removeOrders(orgJpql));
		return countJpql;
	}

	private String removeSelect(String jpql) {
		return jpql.substring(jpql.toLowerCase().indexOf("from"));
	}

	private String removeOrders(String jpql) {
		Matcher m = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE).matcher(jpql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

}