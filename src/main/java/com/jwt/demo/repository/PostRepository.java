package com.jwt.demo.repository;

import com.jwt.demo.entities.Category;
import com.jwt.demo.entities.Post;
import com.jwt.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

  List<Post> findByCategory(Category category);

  List<Post> findByUser(User user);
}
