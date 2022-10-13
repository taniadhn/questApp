package com.dehghan.questapp.repos;

import com.dehghan.questapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findByUserId(Long userId);
}
