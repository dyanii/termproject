package com.example.demo1.dto;

import com.example.demo1.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
    private String id;
    private String userId;
    private String title;
    private String author;
    private String publisher;

    public TodoDTO(final TodoEntity entity) {
        this.id= entity.getId();
        this.userId= entity.getUserId();
        this.title= entity.getTitle();
        this.author= entity.getAuthor();
        this.publisher= entity.getPublisher();
    }

    public static TodoEntity toEntity(final TodoDTO dto) {
        return TodoEntity.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publisher(dto.getPublisher())
                .build();
    }
}
