package com.example.legacy.endpoint;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class TomcatService {

	static Tomcat tomcat;
	
	public static void startServer(String appRoot,String rootDirectory) throws LifecycleException,ServletException {
		tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.addWebapp("/app", new File("WebContent").getAbsolutePath()).getServletContext().setInitParameter("spring.profiles.active", "test");
		tomcat.start();
	}
	
	public static void stopServer() throws LifecycleException {
		tomcat.getServer().stop();
	}	
	
}
