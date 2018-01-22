package com.example.legacy.endpoint;

import static org.junit.Assert.assertEquals;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

public class SessionLegacyServletIT extends TomcatService {
	
	String url = URL + "home";
	
	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);
		assertEquals(200,client.execute(get).getStatusLine().getStatusCode());
		((CloseableHttpClient) client).close();
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity("{\"key\":\"value\"}"));
		assertEquals(201,client.execute(post).getStatusLine().getStatusCode());
		((CloseableHttpClient) client).close(); 
	}

}
