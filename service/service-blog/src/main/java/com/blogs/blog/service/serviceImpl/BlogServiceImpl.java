package com.blogs.blog.service.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogs.blog.dto.BlogDto;
import com.blogs.blog.entity.Blog;
import com.blogs.commonutils.utils.Result;
import com.blogs.blog.mapper.BlogMapper;
import com.blogs.blog.service.BlogService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 21380
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveBlog(BlogDto blogDto) {
        return null;
    }
}
