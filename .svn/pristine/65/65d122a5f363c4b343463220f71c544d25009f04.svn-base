package org.hy.foundation.common.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ExceptionHandleServlet
 */
public class ExceptionHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExceptionHandleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RuntimeException ex=(RuntimeException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		
		RestRuntimeException e=new RestRuntimeException();
		e.setErrorCode(998);
		e.setReason(ex.getMessage());
		e.setStackTrace(ex.getStackTrace());
		
		Gson gson=new Gson();
		String resutl=gson.toJson(e);
		PrintWriter out = response.getWriter();
		out.write(resutl);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
