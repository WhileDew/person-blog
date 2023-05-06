package com.blogs.blog.service.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogs.blog.entity.Comment;
import com.blogs.blog.mapper.CommentMapper;
import com.blogs.blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>  implements CommentService {

}
