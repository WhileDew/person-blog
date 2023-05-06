package com.blogs.email.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 邮箱实体类
 * @author 21380
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    /**
     * 发送邮箱列表
     */
    private String email;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;
}
