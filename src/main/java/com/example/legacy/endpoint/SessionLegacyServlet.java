package com.example.legacy.endpoint;

import java.io.BufferedReader;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SessionLegacyServlet extends HttpServlet {

	private ObjectMapper mapper;
	
	@Override
	public void init() throws ServletException {
		mapper = new ObjectMapper();
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("hello","world");
		Enumeration<String> names = request.getSession().getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			map.put(name, request.getSession().getAttribute(name).toString());
		}//end while
		PrintWriter writer = response.getWriter();
		writer.print(mapper.writeValueAsString(map));
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get the map from the string and set it into the session
		BufferedReader reader = request.getReader();
		StringBuffer buffer = new StringBuffer();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}//end while
		//now convert
		Map<String,String> payload = mapper.readValue(buffer.toString(), new TypeReference<Map<String,String>>() {});
		//set into the session
		HttpSession session = request.getSession();
		for (String key : payload.keySet()) {
			session.setAttribute(key, payload.get(key));
		}//end for
		//set
		response.setStatus(201);
		
	}
	
}
