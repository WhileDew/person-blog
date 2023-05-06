package com.blogs.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogs.blog.dto.BlogDto;
import com.blogs.blog.entity.Blog;
import com.blogs.commonutils.utils.Result;
import com.blogs.blog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 21380
 */
@Api("博客控制器")
@RestController
@RequestMapping(("/blog"))
public class BlogController {

    @Resource
    private BlogService blogService;

    @ApiOperation(value = "首页查询博客")
    @GetMapping("indexBlogs/{current}/{pageSize}")
    public Result indexBlogs(@PathVariable("current") Long current,
                             @PathVariable("pageSize") Long pageSize) {

        Page<Blog> page = new Page<>(current, pageSize);
        blogService.page(page);
        page.setTotal(blogService.count());
        return Result.success(page);
    }

    @ApiOperation(value = "发布博客")
    @PostMapping("saveBlog")
    public Result saveBlog(@RequestBody BlogDto blogDto) {
        return blogService.saveBlog(blogDto);
    }
}
