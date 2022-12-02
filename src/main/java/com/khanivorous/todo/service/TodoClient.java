package com.khanivorous.todo.service;

import com.khanivorous.todo.model.Todo;
import org.springframework.stereotype.Service;

@Service
public interface TodoClient {

    Todo getTodoById(int id);

}
