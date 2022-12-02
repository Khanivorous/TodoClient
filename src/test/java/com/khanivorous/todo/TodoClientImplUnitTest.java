package com.khanivorous.todo;


import com.khanivorous.todo.model.Todo;
import com.khanivorous.todo.service.TodoClientImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoClientImplUnitTest {

    @Mock
    RestTemplate restTemplate;

    TodoClientImpl serviceUnderTest;

    @BeforeEach
    public void setUp() {
        serviceUnderTest = new TodoClientImpl(restTemplate);
    }

    @Test
    void getTodoById() {

        Todo mockResponse = new Todo(
                1,
                1,
                "Test title",
                "Test body");

        when(restTemplate.getForObject("/posts/1", Todo.class))
                .thenReturn(mockResponse);

        Todo response = serviceUnderTest.getTodoById(1);

        assertEquals(1, response.id());
        assertEquals("Test body", response.body());

    }



}
