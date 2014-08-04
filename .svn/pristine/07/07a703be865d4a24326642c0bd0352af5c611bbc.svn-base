package org.hy.foundation.common.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class RestExceptionMapper implements ExceptionMapper<RuntimeException> {

	final Logger logger = Logger.getLogger(RestExceptionMapper.class);
	
//    @Context
//    private HttpHeaders headers;

	public Response toResponse(RuntimeException error) {
		// TODO Auto-generated method stub
		logger.error("服务层异常保存", error);
		RestRuntimeException e=new RestRuntimeException(error.getMessage(),"",javax.ws.rs.core.Response.Status.ACCEPTED,102);
		return Response.status(e.getStatus()).entity(e).type("application/json").build();
	}

}
