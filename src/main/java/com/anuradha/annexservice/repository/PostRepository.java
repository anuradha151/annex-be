package com.anuradha.annexservice.repository;

import com.anuradha.annexservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
