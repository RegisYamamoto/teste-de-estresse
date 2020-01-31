package com.regis.testedeestresse.util;

import java.nio.charset.Charset;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestUtil {
	
	public static final String URL1 = "http://10.251.22.12:8080/selinhos-api/api/campanhas/vigentes";

	public HttpEntity<String> testeGet() {
		HttpHeaders headers = getHttpHeadersAuth();
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate.exchange(String.format(URL1), HttpMethod.GET, request, String.class);
	}
	
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = 
				new HttpComponentsClientHttpRequestFactory(
						HttpClientBuilder.create()
						.setProxy(new HttpHost("proxy.muffato.com.br", 3128, "http"))
						.build());
		clientHttpRequestFactory.setConnectTimeout(500000);
		return clientHttpRequestFactory;
	}

	private HttpHeaders getHttpHeadersAuth() {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;

			{
				set("Content-type", "application/json");
				set("Authorization", "c045cf0d3e092490886975c96c31a1295b20d67eaf5f1509cdf3a04d532dd25f");
			}
		};
	}
	
}