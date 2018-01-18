package com.example.legacy.endpoint;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class TomcatService {

	static Tomcat tomcat;
	
	static String appRoot = "/app";
	
	static String docRoot = "WebContent";	
	
	static String URL = "http://localhost:8080/app/";
	
	@BeforeClass
	public static void before() throws Exception {
		startServer(appRoot,docRoot);
	}
	
	@AfterClass
	public static void after() throws Exception {
		stopServer();
	}	
	
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
