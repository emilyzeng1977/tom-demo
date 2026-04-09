package com.aiprime.bookstore.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.aiprime.bookstore.api.logging.LogbookConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookstoreApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private LogbookConfiguration logbookConfiguration;

	@Test
	void contextLoads() {
		// 应用上下文能正常加载
	}

	@Test
	void logbookConfigurationLoads() {
		assertThat(logbookConfiguration).isNotNull();
	}

	@Test
	void healthEndpointReturnsOk() {
		String body = this.restTemplate.getForObject("http://localhost:" + port + "/health", String.class);
		assertThat(body).isEqualTo("Health Check OK");
	}

	@Test
	void helloEndpointReturnsDefault() {
		String body = this.restTemplate.getForObject("http://localhost:" + port + "/hello", String.class);
		assertThat(body).isEqualTo("Hello World!");
	}

	@Test
	void helloEndpointReturnsCustomName() {
		String body = this.restTemplate.getForObject("http://localhost:" + port + "/hello?name=Tom", String.class);
		assertThat(body).isEqualTo("Hello Tom!");
	}
}
