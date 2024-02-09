package com.springboot.blog.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import com.springboot.blog.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostServiceImpl postServiceImpl;

    @Test
    public void test_getAllPosts(){
        List<Post> myPosts =new ArrayList<Post>();
        myPosts.add(new Post(1L,"This is my 1st post","1st post description","This is my 1st post"));
        myPosts.add(new Post(2L,"This is my 2nd post","2nd post description","This is my 2nd post"));
        when(postRepository.findAll()).thenReturn(myPosts);
        assertEquals(2,postServiceImpl.getAllPosts().size());

    }
}
