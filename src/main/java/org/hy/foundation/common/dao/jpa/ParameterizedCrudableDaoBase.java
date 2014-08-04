package org.hy.foundation.common.dao.jpa;

import java.util.List;

import org.criteria4jpa.Criteria;
import org.criteria4jpa.criterion.Criterion;
import org.criteria4jpa.projection.Projections;
import org.hy.foundation.common.entity.jpa.BasicEntity;
import org.hy.foundation.utils.filter.PropertyFilter;
import org.hy.foundation.utils.page.Page;
import org.hy.foundation.utils.page.Page.Sort;

public class ParameterizedCrudableDaoBase<T extends BasicEntity> extends CrudableDaoUtils<T> implements 
		ParameterizedCrudable<T> {

	public ParameterizedCrudableDaoBase() {
		super();
	}

	public void newEntity(Class<T> entityClass, T t) {
		entityManager.persist(t);
	}

	public void updateEntity(Class<T> entityClass, T t) {
		entityManager.merge(t);
	}

	public void deleteEntityByPrimarykey(Class<T> entityClass, Object primaryKey) {
		entityManager.remove(getEntityByPrimarykey(entityClass, primaryKey));
	}

	public T getEntityByPrimarykey(Class<T> entityClass, Object primaryKey) {
		return (T) entityManager.find(entityClass, primaryKey);
	}

	public List<T> findAllEntities(Class<T> entityClass, List<Sort> sorts){
		return this.setSortToCriteria(this.createCriteria(entityClass), sorts).getResultList();
	}

	// =============Advanced Read for List of Entities================
	public List<T> findEntitiesByProperties(Class<T> entityClass, String filterStr){
		return findEntitiesInPageByProperties(entityClass, PropertyFilter.parsePropertyFilterExp(filterStr), null);
	}
	
	public List<T> findEntitiesInPageByProperties(Class<T> entityClass, String filterStr, Page page){
		return findEntitiesInPageByProperties(entityClass, PropertyFilter.parsePropertyFilterExp(filterStr), page);
	}
	
	public Page findEntitiesAsPageByProperties(Class<T> entityClass, String filterStr, Page page){
		List<PropertyFilter> filters=PropertyFilter.parsePropertyFilterExp(filterStr);
		return findEntitiesAsPageByProperties(entityClass, filters, page);
	}
	
	public Page findEntitiesAsPageByProperties(Class<T> entityClass, List<PropertyFilter> filters, Page page){
		return page.setResult(findEntitiesInPageByProperties(entityClass, filters, page))
				.setTotalCount(this.countPageOfEntities(entityClass, filters));
	}

	public long countPageOfEntities(Class<T> entityClass, String filterStr){
		return countPageOfEntities(entityClass, PropertyFilter.parsePropertyFilterExp(filterStr));
	}
	
	public long countPageOfEntities(Class<T> entityClass, List<PropertyFilter> filters){
		Criterion[] cns = null;
		if (filters != null && filters.size() > 0) {
			cns = buildCriterionByPropertyFilter(filters);
		}

		Criteria c = this.createCriteria(entityClass, cns);

		return (Long) c.setProjection(Projections.rowCount()).getSingleResult();
	}

	// =============Reused method by crudable implementation================
	protected List<T> findEntitiesInPageByProperties(Class<T> entityClass, List<PropertyFilter> filters, Page page) {
		Criterion[] cns = null;
		if (filters != null && filters.size() > 0) {
			cns = buildCriterionByPropertyFilter(filters);
		}

		Criteria c = this.createCriteria(entityClass, cns);
		if (page != null)
			this.setPageToCriteria(c, page);

		return c.getResultList();
	}

}
