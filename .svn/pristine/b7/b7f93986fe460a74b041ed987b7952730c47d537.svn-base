package org.hy.foundation.common.ws.restful;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hy.foundation.common.entity.jpa.BasicEntity;
import org.hy.foundation.utils.page.Page;
import org.hy.foundation.utils.page.Page.Sort;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.springframework.transaction.annotation.Transactional;

public interface ICrudableRestful<T extends BasicEntity> {
	
	/**
	 * 新增实体
	 * @param entity
	 */
	@POST
	@Consumes({ "application/json;charset=UTF-8"})
	@Transactional
	public T newEntity(T entity);
	
	/**
	 * 更新实体
	 * @param entity
	 */
	@PUT
	@Path("{id}")
	@Consumes({ "application/json;charset=UTF-8" })
	@Transactional
	public T updateEntity(T entity);
	
	/**
	 * 根据Id删除实体
	 * @param id
	 */
	@DELETE
	@Path("{id}")
	@Transactional
	public void deleteEntityById(@PathParam("id") Integer id);

	/**
	 * Read Operation for Single Entity
	 * 根据Id查询实体
	 * @return entity
	 */
	@GET
	@Path("{id}")
	@Produces({ "application/json;charset=UTF-8"})
	public T getEntityById(@PathParam("id") Integer id);
	
	/**
	 * 返回缺省实体
	 * @param entity
	 */
	@GET
	@Path("default")
	@Produces({ "application/json;charset=UTF-8"})
	public T getDefaultEntity();
	
	/**
	 * Read Operation for List of Entities
	 * 查询所有的实体
	 * @return List<T>
	 */
	@GET
	@Path("all")
	@Produces({ "application/json;charset=UTF-8"})
	@Wrapped
	public List<T> findAllEntities(@QueryParam("sorts") List<Sort> sorts);

	/**
	 * Read Operation for List of Entities
	 * 查询指定页的实体
	 * url: .../inPage?&startPage=1&numPage=10&orderBy=descending&filter="{名字值对序列}"
	 * 名字值对序列: propertyName1_EQ_propertyValue1,propertyName2_GT_propertyValue2
	 * @return List<T>
	 */
	@GET
	@Path("inPage")
	@Produces({ "application/json;charset=UTF-8"})
	public List<T> findEntitiesInPageByProperties(
			@QueryParam("startPage") String startPage,
			@QueryParam("numPage") String numPage,
			@QueryParam("orderBy") String orderBy,
			@QueryParam("filter") String filterStr);
	
	@GET
	@Path("pageOf")
	@Produces({ "application/json;charset=UTF-8"})
	public Page findEntitiesAsPageByProperties(
			@QueryParam("startPage") String startPage,
			@QueryParam("numPage") String numPage,
			@QueryParam("orderBy") String orderBy,
			@QueryParam("filter") String filterStr);
	
	@GET
//	@Path("dojoPageOf")
	@Produces({ "application/json"})
//	@ResponseObject
	public Response findEntitiesAsDojoPage(
			@HeaderParam("Range") String range,
			@Context UriInfo queryInfo);
	
//	@GET
//	@Path("dojoPageOf2")
//	@Produces({ "application/json;charset=UTF-8"})
//	@ResponseObject
//	public Response findEntitiesAsDojoPage(
//			@HeaderParam("items") String items,
//			@QueryParam("sortBy") String sortBy,
//			@QueryParam("filterStr") String filterStr);
	
	@GET
	@Path("countInPage")
	@Produces({ "application/json;charset=UTF-8"})
	public long countPageOfEntities(@QueryParam("filter") String filterStr);
	
}
