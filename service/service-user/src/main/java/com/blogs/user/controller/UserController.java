package com.blogs.user.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.auth0.jwt.interfaces.Claim;
import com.blogs.commonutils.utils.JwtUtils;
import com.blogs.user.dto.UserDto;
import com.blogs.user.entity.User;
import com.blogs.user.service.UserService;
import com.blogs.commonutils.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 21380
 */
@Api("用户控制器")
@RestController
@RequestMapping(("/user"))
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("根据id获取用户信息")
    @GetMapping("{id}")
    public Result getUserById(@PathVariable("id") Long id) {
        return Result.success(userService.getById(id));
    }

    @ApiOperation("上传头像")
    @PostMapping("uploadAvatar")
    public Result uploadAvatar(MultipartFile file, HttpServerRequest request) {
        System.out.println(file);
        System.out.println(request);
        String token = request.getHeader("token");
        // 对token进行解密
        Map<String, Claim> map = JwtUtils.validateToken(token);
        // 判断字符串是否有效
        if (map == null) {
            return Result.error("该用户未登录");
        }
        return userService.uploadAvatar(file, map.get("id").asLong());
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public Result register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @ApiOperation("根据token字符串获取用户信息")
    @GetMapping("getUserInfo")
    public Result getUserInfo(HttpServerRequest request) {
        String token = request.getHeader("token");
        // 对token进行解密
        Map<String, Claim> map = JwtUtils.validateToken(token);
        // 判断字符串是否有效
        if (map == null) {
            return Result.error("该用户未登录");
        }
        User user = new User();
        user.setId(map.get("id").asLong());
        user.setUsername(map.get("username").asString());
        user.setUsername(map.get("avatar").asString());
        user.setUsername(map.get("email").asString());
        return Result.success(user);
    }
}
