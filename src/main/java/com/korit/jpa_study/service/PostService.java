package com.korit.jpa_study.service;

import com.korit.jpa_study.dto.Request.AddPostReqDto;
import com.korit.jpa_study.dto.Request.EditPostReqDto;
import com.korit.jpa_study.dto.Response.ApiRespDto;
import com.korit.jpa_study.entity.Post;
import com.korit.jpa_study.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public ApiRespDto<?> add(AddPostReqDto addPostReqDto) {
        Optional<Post> foundPost = postRepository.findByTitle(addPostReqDto.getTitle());
        if (foundPost.isPresent()) {
            return new ApiRespDto<>("failed", "중복된 이름", null);
        }
        return new ApiRespDto<>("success", "추가 완료", postRepository.save(addPostReqDto.toEntity()));
    }

    public ApiRespDto<?> getPostAll() {
        return new ApiRespDto<>("success", "전체 조회 완료", postRepository.findAll());
    }

    public ApiRespDto<?> getPostByPostId(Integer postId) {
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다,", null);
        }

        return new ApiRespDto<>("success", "post 조회 완료", foundPost);
    }

    public ApiRespDto<?> getPostListByUserId(Integer userId) {
        return new ApiRespDto<>("success", "게시물 조회 완료", postRepository.findAllByUserId(userId));
    }

    public ApiRespDto<?> editPost(EditPostReqDto editPostReqDto) {
        Optional<Post> foundPost = postRepository.findById(editPostReqDto.getPostId());
        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다,", null);
        }
        Post post = foundPost.get();/*.builder().build();*/
        post.setTitle(editPostReqDto.getTitle());
        post.setContent(editPostReqDto.getContent());
        post.setUpdateDt(LocalDateTime.now());
        return new ApiRespDto<>("success", "post 수정 완료", postRepository.save(post));
    }

    public ApiRespDto<?> removePost(Integer postId) {
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다,", null);
        }
        postRepository.deleteById(postId);
        return new ApiRespDto<>("success", "post 삭제 완료", null);
    }
}
