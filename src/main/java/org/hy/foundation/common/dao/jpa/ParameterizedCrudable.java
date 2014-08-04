package org.hy.foundation.common.dao.jpa;

import java.util.List;

import org.hy.foundation.common.entity.jpa.BasicEntity;
import org.hy.foundation.utils.filter.PropertyFilter;
import org.hy.foundation.utils.page.Page;
import org.hy.foundation.utils.page.Page.Sort;

public interface ParameterizedCrudable<T extends BasicEntity> {

	// =============Basic CUD Operations================
	public void newEntity(Class<T> entityClass, T t);
	
	public void updateEntity(Class<T> entityClass, T t);
	
	public void deleteEntityByPrimarykey(Class<T> entityClass, Object primaryKey);
	
	// =============Basic Read for Single Entity================
	public T getEntityByPrimarykey(Class<T> entityClass, Object primaryKey);
	
	// =============Basic Read for List of Entities================
	public List<T> findAllEntities(Class<T> entityClass, List<Sort> sorts);
	
	// =============Advanced Read for List of Entities================
	public List<T> findEntitiesByProperties(Class<T> entityClass, String filterStr);
	
	public List<T> findEntitiesInPageByProperties(Class<T> entityClass, String filterStr, Page page);
	
	public Page findEntitiesAsPageByProperties(Class<T> entityClass, String filterStr, Page page);
	
	public Page findEntitiesAsPageByProperties(Class<T> entityClass, List<PropertyFilter> filters, Page page);
	
	public long countPageOfEntities(Class<T> entityClass, String filterStr);
}
