package com.anji.mock.wiremockdemo.client;

import static java.lang.String.valueOf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RedcrossClientImpl implements RedcrossClient {

	private static final String PATH = "/v1/service/hours";

	@Value("${redcross.url}")
	private String redcrossHost;

	@Override
	public Double getSocialServiceHours(Long studentId, String collegeId) {

		String urlTemplate = UriComponentsBuilder.fromHttpUrl(redcrossHost + PATH).queryParam("student", studentId)
				.queryParam("college", collegeId).encode().toUriString();

		RestTemplate restTemplate = new RestTemplate();

		Map<String, String> params = new HashMap<>();
		params.put("college", collegeId);
		params.put("student", valueOf(studentId));

		ResponseEntity<Double> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, null, Double.class,
				params);

		return response.getBody();
	}
}
