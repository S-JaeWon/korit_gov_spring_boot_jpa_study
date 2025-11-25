package com.korit.jpa_study.repository;

import com.korit.jpa_study.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// jpa 사용시 Repository는 인터페이스로
public interface PostRepository extends JpaRepository<Post/*엔티티 타입*/, Integer/*기본키 타입*/> {
    Optional<Post> findByTitle(String title);
    List<Post> findAllByUserId(Integer userId);
}
