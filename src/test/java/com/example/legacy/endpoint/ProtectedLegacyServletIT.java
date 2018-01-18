package com.example.legacy.endpoint;

import static org.junit.Assert.*;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProtectedLegacyServletIT {

	static String appRoot = "/app";
	
	static String docRoot = "WebContent";
	
	@BeforeClass
	public static void before() throws Exception {
		TomcatService.startServer(appRoot,docRoot);
	}
	
	@AfterClass
	public static void after() throws Exception {
		TomcatService.stopServer();
	}	
	
	@Test
	public void testDoGetHttpServletRequestHttpServletResponseNotAuthenticated() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet("http://localhost:8080/" + appRoot + "/admin");
		assertEquals(200,client.execute(get).getStatusLine().getStatusCode());
		((CloseableHttpClient) client).close();		
	}

}
