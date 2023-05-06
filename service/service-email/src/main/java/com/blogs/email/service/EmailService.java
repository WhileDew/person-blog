package com.blogs.email.service;

import com.blogs.commonutils.utils.Result;
import com.blogs.email.entity.Email;

/**
 * 邮箱服务接口
 *
 * @author 21380
 */
public interface EmailService {

    /**
     * 发送邮件
     * @param email 邮箱列表
     */
    Result send(String email);
}


