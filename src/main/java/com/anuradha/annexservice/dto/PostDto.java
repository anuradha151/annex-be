package com.anuradha.annexservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class PostDto {

    private String id;
    private String name;
    private String description;
    private String contactNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
