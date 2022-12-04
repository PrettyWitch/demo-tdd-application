package com.tw.capability.gtb.demotddapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootTestHelloControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void should_return_hello_world() {
        // given

        // when
        ResponseEntity<String> response = restTemplate.getForEntity("/hello", String.class);

        // then
        assertThat(response.getBody()).isEqualTo("Hello World!");
    }
}
