package com.example.legacy.endpoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProtectedLegacyServlet extends HttpServlet {

	private static final Log logger = LogFactory.getLog(ProtectedLegacyServlet.class);
	
	private ObjectMapper mapper;
	
	@Override
	public void init() throws ServletException {
		mapper = new ObjectMapper();
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("protected","endpoint");
		Enumeration<String> names = request.getSession().getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			map.put(name, request.getSession().getAttribute(name).toString());
		}//end while
		PrintWriter writer = response.getWriter();
		writer.print(mapper.writeValueAsString(map));
		//dump the session to the console
		dumpSession(request.getSession());
	}
		
	
	private void dumpSession(HttpSession session) {
		System.out.println(session.getId());
		
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println(name + " " + session.getAttribute(name));
		}//end while
		
	}

	
}
