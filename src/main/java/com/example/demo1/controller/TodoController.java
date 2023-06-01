package com.example.demo1.controller;

import com.example.demo1.dto.ResponseDTO;
import com.example.demo1.dto.TodoDTO;
import com.example.demo1.model.TodoEntity;
import com.example.demo1.service.TodoService;
import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("book")
public class TodoController {
    @Autowired
    private TodoService service;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
        try {

            TodoEntity entity = TodoDTO.toEntity(dto);

            entity.setId(null);
            List<TodoEntity> entities = service.create(entity);
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new)
                    .collect(Collectors.toList());
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodo() {
        String temporaryUserId = "KimDaEun";

        List<TodoEntity> entities = service.retrieve(temporaryUserId);
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new)
                .collect(Collectors.toList());
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                .data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {

        TodoEntity entity = TodoDTO.toEntity(dto);
        TodoEntity updatedEntity = service.update(entity);
        if(updatedEntity == null) {
            return ResponseEntity.notFound().build();
        }

        TodoDTO updatedDTO = new TodoDTO(updatedEntity);
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                .data(Collections.singletonList(updatedDTO)).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryUserId = "KimDaEun";

            TodoEntity entity = TodoDTO.toEntity(dto);
            entity.setUserId(temporaryUserId);
            List<TodoEntity> entities =service.delete(entity);
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new)
                    .collect(Collectors.toList());
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
