package com.example.springexample.dto;

import com.example.springexample.entity.Comment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private long rating;
    private List<Comment> comments = new ArrayList<>();
}
