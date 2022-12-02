package com.khanivorous.todo.controller;

import com.khanivorous.todo.model.Todo;
import com.khanivorous.todo.service.TodoClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/todo")
public class TodoController {

    private TodoClient todoClient;

    @Autowired
    public TodoController(TodoClient todoClient) {
        this.todoClient = todoClient;
    }

    @Operation(summary = "Get todo by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "found todo",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Todo.class)) }
            ),
            @ApiResponse(responseCode = "404", description = "Todo not found",
                    content =  @Content)})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Todo getUserById(@Parameter(description = "id of Todo to be searched") @PathVariable Integer id) {
        return todoClient.getTodoById(id);
    }
}
