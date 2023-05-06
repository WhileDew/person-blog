package com.blogs.email.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blogs.commonutils.utils.Result;
import com.blogs.email.entity.Email;
import com.blogs.email.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 邮箱控制器
 * @author 21380
 */
@Api("邮箱控制器")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Resource
    private EmailService emailService;

    @ApiOperation("发送验证码")
    @PostMapping("sendCode")
    public Result sendCode(@RequestParam(value = "email") String email) {
        System.out.println(email);
        // 判空
        if (StringUtils.isBlank(email)) {
            return Result.error("邮箱不能为空");
        }
        // 发送验证码
        return Result.success(emailService.send(email));
    }
}
