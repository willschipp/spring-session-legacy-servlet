package com.example.legacy.endpoint;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

public class ProtectedLegacyServletIT extends TomcatService {
	
	static final String SECURITY_CHECK = "j_spring_security_check";
	
	String url = URL + "admin";
	
	@Test
	public void testDoGetHttpServletRequestHttpServletResponseNotAuthenticated() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		boolean authPage = false;
		String line;
		while ((line = br.readLine()) != null) {
			if (line.contains(SECURITY_CHECK)) {
				authPage = true;
				break;
			}//end if
		}//end while
		br.close();
		((CloseableHttpClient) client).close();		
		assertTrue(authPage);
	}

}
