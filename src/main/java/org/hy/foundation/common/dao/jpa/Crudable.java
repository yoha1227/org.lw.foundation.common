package org.hy.foundation.common.dao.jpa;

import java.util.Collection;
import java.util.List;

import org.hy.foundation.common.entity.jpa.BasicEntity;
import org.hy.foundation.utils.filter.PropertyFilter;
import org.hy.foundation.utils.page.Page;
import org.hy.foundation.utils.page.Page.Sort;


public interface Crudable<T extends BasicEntity> {

	// =============Basic CUD Operations================
	public void newEntity(T t);

	public void updateEntity(T t);

	public void deleteEntity(T t);

	public void deleteEntityByPrimarykey(Object primaryKey);
	
	// =============Basic Read for Single Entity================
	public T getEntityByProperties(List<PropertyFilter> filters);

	// =============Basic Read for List of Entities================
	public List<T> findEntitiesByPrimarykeys(Collection<Object> primaryKeys, List<Sort> sorts);

	public List<T> findAllEntities(List<Sort> sorts);

	// =============Advanced Read for List of Entities================
	public List<T> findEntitiesByProperties(List<PropertyFilter> filters);

	
	public List<T> findEntitiesInPageByProperties(List<PropertyFilter> filters, Page page);

	
	public Page findEntitiesAsPageByProperties(List<PropertyFilter> filters, Page page);

//	public List findSql(String sql);
	
	public long countPageOfEntities(List<PropertyFilter> filters);

}
