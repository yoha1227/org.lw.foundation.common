package org.hy.foundation.common.dao.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.criteria4jpa.Criteria;
import org.criteria4jpa.criterion.Criterion;
import org.criteria4jpa.criterion.Restrictions;
import org.hy.foundation.common.entity.jpa.BasicEntity;
import org.hy.foundation.utils.filter.PropertyFilter;
import org.hy.foundation.utils.page.Page;
import org.hy.foundation.utils.page.Page.Sort;

@SuppressWarnings({ "unchecked" })
public class CrudableDaoBase<T extends BasicEntity> extends
		ParameterizedCrudableDaoBase<T> implements Crudable<T> {

	protected Class<T> entityClass = null;

	public CrudableDaoBase() {
		super();
		if (this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
			entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
	}

	public void newEntity(T t) {
		entityManager.persist(t);
	}

	public void updateEntity(T t) {
		entityManager.merge(t);
	}

	public void deleteEntity(T t) {
		entityManager.remove(t);
	}

	public void deleteEntityByPrimarykey(Object primaryKey) {
		deleteEntityByPrimarykey(entityClass, primaryKey);
	}

	public T getEntityByPrimarykey(Object primaryKey) {
		return (T) entityManager.find(entityClass, primaryKey);
	}

	public T getEntityByProperties(List<PropertyFilter> filters) {
		if (findEntitiesByProperties(filters) == null
				|| findEntitiesByProperties(filters).size() == 0) {
			return null;
		}
		return findEntitiesByProperties(filters).get(0);
	}

	public List<T> findEntitiesByPrimarykeys(Collection<Object> primaryKeys, List<Sort> sorts) {
		return this.setSortToCriteria(
				this.createCriteria(Restrictions.in("id", primaryKeys)), sorts)
				.getResultList();
	}

	public List<T> findAllEntities(List<Sort> sorts) {
		return this.setSortToCriteria(this.createCriteria(), sorts)
				.getResultList();
	}

	public List<T> findEntitiesByProperties(List<PropertyFilter> filters) {
		return findEntitiesInPageByProperties(filters, null);
	}

	public List<T> findEntitiesInPageByProperties(List<PropertyFilter> filters, Page page) {
		return findEntitiesInPageByProperties(entityClass, filters, page);
	}

	public Page findEntitiesAsPageByProperties(List<PropertyFilter> filters, Page page) {
		return findEntitiesAsPageByProperties(entityClass, filters, page);
	}

	public long countPageOfEntities(List<PropertyFilter> filters) {
		return countPageOfEntities(entityClass, filters);
	}

	// =============Reused method by crudable implementation================
	protected Criteria createCriteria(final Criterion... criterions) {
		return createCriteria(entityClass, criterions);
	}

	protected Criteria createCriteria(final List<PropertyFilter> filters) {
		return createCriteria(entityClass, filters);
	}


}