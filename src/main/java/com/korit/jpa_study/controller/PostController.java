package com.korit.jpa_study.controller;

import com.korit.jpa_study.dto.Request.AddPostReqDto;
import com.korit.jpa_study.dto.Request.EditPostReqDto;
import com.korit.jpa_study.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* JPA
* Java Persistence Api
* 자바와 관계형 DB 테이블간 자동 매핑
* -> SQL을 직접 쓰지 않고 자바 객체 중심으로 DB를 다룸, sql 쿼리 없이 빠르게 crud 가능
* 대신, 복잡한 쿼리는 어렵고 디버깅 힘듦
*
* */

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody AddPostReqDto addPostReqDto) {
        return ResponseEntity.ok(postService.add(addPostReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPostAll() {
        return ResponseEntity.ok(postService.getPostAll());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostByPostId(@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.getPostByPostId(postId));
    }

    @GetMapping("/get/userId")
    public ResponseEntity<?> getPostListByUserId(@RequestParam Integer userId) {
        return ResponseEntity.ok(postService.getPostListByUserId(userId));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editPost(@RequestBody EditPostReqDto editPostReqDto) {
        return ResponseEntity.ok(postService.editPost(editPostReqDto));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removePost(@RequestParam Integer postId) {
        return ResponseEntity.ok(postService.removePost(postId));
    }
}
