package com.khanivorous.todo.service;

import com.khanivorous.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TodoClientImpl implements TodoClient {


    private RestTemplate restTemplate;

    @Autowired
    public TodoClientImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Todo getTodoById(int id) {
        return restTemplate.getForObject("/posts/" + id, Todo.class);
    }
}
