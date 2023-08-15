package com.anuradha.annexservice.repository;

import com.anuradha.annexservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
