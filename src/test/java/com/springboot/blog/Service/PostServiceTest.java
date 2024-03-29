package com.springboot.blog.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import com.springboot.blog.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostServiceImpl postServiceImpl;



    @Test
    @Order(1)
    public void test_createPost(){
        // Given
        PostDto postDto = new PostDto();
        postDto.setTitle("Test Post");
        postDto.setDescription("Test Post Description");
        postDto.setContent("Test Post Content");

        Post savedPost = new Post(1L, "Test Post", "Test Post Description", "Test Post Content");

        // Mock the behavior of the postRepository.save() method
        when(postRepository.save(any(Post.class))).thenReturn(savedPost);

        // When
        PostDto createdPost = postServiceImpl.createPost(postDto);

        // Then
        assertEquals(postDto.getTitle(), createdPost.getTitle());
        assertEquals(postDto.getDescription(), createdPost.getDescription());
        assertEquals(postDto.getContent(), createdPost.getContent());
    }



    @Test
    @Order(2)
    public void test_getAllPosts() {
        List<Post> myPosts = new ArrayList<Post>();
        myPosts.add(new Post(1L, "This is my 1st post", "1st post description", "This is my 1st post"));
        myPosts.add(new Post(2L, "This is my 2nd post", "2nd post description", "This is my 2nd post"));
        when(postRepository.findAll()).thenReturn(myPosts);
        assertEquals(2, postServiceImpl.getAllPosts().size());
    }

    @Test
    @Order(3)
    public void test_getPostById() {
        Post post = new Post(1L, "This is my 1st post", "1st post description", "This is my 1st post");
        Long id = 1L;
        when(postRepository.findById(id)).thenReturn(Optional.of(post));
        assertEquals(id, postServiceImpl.getPostById(id).getId());
    }

}
