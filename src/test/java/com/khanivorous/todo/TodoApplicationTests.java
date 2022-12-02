package com.khanivorous.todo;

import com.khanivorous.todo.model.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag("Application")
@TestPropertySource("classpath:application-test.properties")
class TodoApplicationTests {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int localServerPort;

    private String baseUrl;

    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + localServerPort;
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @AfterEach
    public void tearDown() {
        mockRestServiceServer.reset();
    }

    @Test
    void getTodoById() throws IOException {

        Resource resource = new ClassPathResource("todo/todoResponse.json");

        InputStream inputStream = resource.getInputStream();

        byte[] dataAsBytes = FileCopyUtils.copyToByteArray(inputStream);

        String dummyResponse = new String(dataAsBytes, StandardCharsets.UTF_8);

        mockRestServiceServer.expect(requestTo("https://fakeuri/posts/1"))
                .andRespond(withSuccess(dummyResponse, MediaType.APPLICATION_JSON));

        ResponseEntity<Todo> response = testRestTemplate.
                getForEntity(baseUrl + "/todo/1", Todo.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().id());
        assertEquals("test body id 1", response.getBody().body());

    }

}
