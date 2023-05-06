package com.blogs.blog.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogs.blog.entity.Tag;
import com.blogs.blog.mapper.TagMapper;
import com.blogs.blog.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
