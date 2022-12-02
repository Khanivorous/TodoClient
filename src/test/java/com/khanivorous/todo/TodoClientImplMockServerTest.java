package com.khanivorous.todo;

import com.khanivorous.todo.model.Todo;
import com.khanivorous.todo.service.TodoClientImpl;
import org.junit.jupiter.api.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Tag("Application")
public class TodoClientImplMockServerTest {

    private final RestTemplate restTemplate = new RestTemplate();

    private  MockRestServiceServer mockRestServiceServer;

    TodoClientImpl serviceUnderTest;

    @BeforeEach
    public void setUp() {
        serviceUnderTest = new TodoClientImpl(restTemplate);
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

        mockRestServiceServer.expect(requestTo("/posts/1"))
                .andRespond(withSuccess(dummyResponse, MediaType.APPLICATION_JSON));

        Todo response = serviceUnderTest.getTodoById(1);

        assertEquals(1, response.id());
        assertEquals("test body id 1", response.body());

    }

}
