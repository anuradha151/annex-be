package com.anuradha.annexservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostDto(
        UUID id,
        String name,
        String desc,
        String contactNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
