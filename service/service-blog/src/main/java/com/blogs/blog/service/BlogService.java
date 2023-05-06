package com.blogs.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blogs.blog.dto.BlogDto;
import com.blogs.blog.entity.Blog;
import com.blogs.commonutils.utils.Result;

public interface BlogService extends IService<Blog> {

    Result saveBlog(BlogDto blogDto);

}
