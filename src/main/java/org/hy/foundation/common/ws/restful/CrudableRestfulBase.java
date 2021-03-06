package org.hy.foundation.common.ws.restful;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.hy.foundation.common.dao.jpa.CrudableDaoUtils.MatchType;
import org.hy.foundation.common.dao.jpa.ParameterizedCrudable;
import org.hy.foundation.common.dao.jpa.ParameterizedCrudableDaoBase;
import org.hy.foundation.common.entity.jpa.BasicEntity;
import org.hy.foundation.utils.filter.PropertyFilter;
import org.hy.foundation.utils.page.Page;
import org.hy.foundation.utils.page.Page.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class CrudableRestfulBase<T extends BasicEntity> implements
		ICrudableRestful<T> {

	private static final Logger LOG = Logger.getLogger(CrudableRestfulBase.class);
	
	private Class<T> entityClass = null;
	private ParameterizedCrudable<T> baseDao = null;

	protected EntityManager entityManager = null;

	public CrudableRestfulBase() {
		super();
		this.entityClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName="CrudableBaseJPA")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Autowired(required=false)
	@Qualifier("parameterizedCrudable") 
	public void setBaseDao(ParameterizedCrudable baseDao) {
		this.baseDao = baseDao;
	}

	public ParameterizedCrudable getBaseDao() {
		if (baseDao == null) {
			ParameterizedCrudableDaoBase basicCrud = new ParameterizedCrudableDaoBase();
			basicCrud.setEntityManager(entityManager);
			baseDao = basicCrud;
		}
		return baseDao;
	}

	public T newEntity(T entity) {
		getBaseDao().newEntity(entityClass, entity);
		return entity;
	}

	public T updateEntity(T entity) {
		getBaseDao().updateEntity(entityClass, entity);
		return (T) getBaseDao().getEntityByPrimarykey(entityClass, entity.getId());
	}

	public void deleteEntityById(Integer id) {
		getBaseDao().deleteEntityByPrimarykey(entityClass, id);
	}

	public T getEntityById(Integer id){
		return (T) getBaseDao().getEntityByPrimarykey(entityClass, id);
	}

	public T getDefaultEntity() {
		try {
			Object invokertester = entityClass.newInstance();
			return (T) entityClass.getMethod("generateDefaultEntity", null).invoke(invokertester, null);
			// return entityClass.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	public long countPageOfEntities(String filterStr) {
		return getBaseDao().countPageOfEntities(entityClass, filterStr);
	}

	public List<T> findAllEntities(List<Sort> sorts) {
		return getBaseDao().findAllEntities(entityClass, sorts);
	}

	public Page findEntitiesAsPageByProperties(String startPage,
			String numPage, String orderBy, String filterStr) {
		Page page=new Page(Integer.parseInt(startPage), Integer.parseInt(numPage), orderBy);
		return getBaseDao().findEntitiesAsPageByProperties(entityClass, filterStr, page);
	}

	public List<T> findEntitiesInPageByProperties(String startPage,
			String numPage, String orderBy, String filterStr) {
		Page page=new Page(Integer.parseInt(startPage), Integer.parseInt(numPage), orderBy);
		return getBaseDao().findEntitiesInPageByProperties(entityClass, filterStr, page);
	}

	@SuppressWarnings("unchecked")
	public Response findEntitiesAsDojoPage(String range, UriInfo queryInfo) {
		List<PropertyFilter> propertyFilters = generatePropertyFilter(queryInfo.getQueryParameters());
		String sortBy=queryInfo.getQueryParameters().getFirst("sortBy");
		Page page=generatePageByDojo(range, sortBy);
		Page rePage=getBaseDao().findEntitiesAsPageByProperties(entityClass, propertyFilters, page);
		return Response.ok((List<T>)rePage.getResult(), MediaType.APPLICATION_JSON).header("Content-Range", getContentRange(range, rePage)).build();
	}
	
	private String getContentRange(String range, Page rePage){
		return range.split("-")[0]+"-"+(rePage.getResult().size()+rePage.getOffset()-1)+"/"+rePage.getTotalCount();
	}
	
	private List<PropertyFilter> generatePropertyFilter(MultivaluedMap<String, String> queryParameters){
		List<PropertyFilter> propertyFilters=new ArrayList<PropertyFilter>();
		for (Entry<String, List<String>> queryParameter : queryParameters.entrySet()) {
			if ("sortBy".equals(queryParameter.getKey())) {
				continue;
			}
			String[] names=queryParameter.getKey().split("\\.");
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < names.length; i++) {
				list.add(names[i]);
			}
			Class cls = getClassByPropertyName(entityClass, list);
			if (null == cls) {
				continue;
			}
			Object propertyValue = generatePropertyValueByClass(cls, queryParameter.getValue().get(0));
			propertyFilters.add(new PropertyFilter(queryParameter.getKey(), propertyValue, MatchType.EQ));
		}
		return propertyFilters;
	}
	
	private Object generatePropertyValueByClass(Class cls, String value){
		if (cls.isEnum()) {
			return Enum.valueOf(cls, value);
		}
		if (Integer.class == cls) {
			return Integer.valueOf(value);
		}
		if (Long.class == cls) {
			return Long.valueOf(value);
		}
		if (Double.class == cls) {
			return Double.valueOf(value);
		}
		if (Float.class == cls) {
			return Float.valueOf(value);
		}
		if (Boolean.class == cls) {
			return Boolean.valueOf(value);
		}
		return value;
	}
	
	private<S extends Enum> Class<S> getClassByPropertyName(Class cls, List<String> list){
		try {
			if (list.isEmpty()) {
				return null;
			}
			if (1<list.size()) {
				Field field=cls.getDeclaredField(list.get(0));
				list.remove(0);
				return getClassByPropertyName(field.getType(), list);
			}else {
				return (Class<S>) cls.getDeclaredField(list.get(0)).getType();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
	private Page generatePageByDojo(String range, String sortBy){
		if (0!=range.indexOf("items=")) {
			throw new RuntimeException("不支持Header ： "+range+"  ,  例：Range:items=0-24");
		}
		String items=range.substring(6);
		String[] item = items.split("-");
		Integer offset=Integer.parseInt(item[0]);
		Integer pageSize=Integer.parseInt(item[1]);
		pageSize=pageSize-offset+1;
		Page page=new Page();
		page.setOffset(offset);
		page.setPageSize(pageSize);
		
		if ( null == sortBy || 0 == sortBy.trim().length() ) {
			return page;
		}
		StringBuffer orderBy=new StringBuffer();
		StringBuffer orderDir=new StringBuffer();
		String[] sortBys=sortBy.split(",");
		for (String sortByTemp : sortBys) {
			orderBy.append(sortByTemp.substring(1)+",");
			if ('+'==sortByTemp.charAt(0)) {
				orderDir.append(Sort.ASC+",");
			}else if ('-'==sortByTemp.charAt(0)) {
				orderDir.append(Sort.DESC+",");
			}
		}
		page.setOrderBy(orderBy.toString());
		page.setOrderDir(orderDir.toString());
		return page;
	}

//	public Response findEntitiesAsDojoPage(String items, String sortBy, String filterStr) {
//		Page page=generatePageByDojo(items, sortBy);
//		Page rePage=getBaseDao().findEntitiesAsPageByProperties(entityClass, filterStr, page);
//		return Response.ok(rePage.getResult(), MediaType.APPLICATION_JSON).header("items", items+"/"+rePage.getTotalCount()).build();
//	}

	
}
