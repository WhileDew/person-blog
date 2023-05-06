package com.blogs.blog.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Blog数据传输对象
 * @author 21380
 */
public class BlogDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "首张图片")
    private String firstPicture;

    @ApiModelProperty(value = "简介")
    private String description;
}
