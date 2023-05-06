package com.blogs.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.blogs.user.dto.UserDto;
import com.blogs.user.entity.User;
import com.blogs.commonutils.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 21380
 */
public interface UserService extends IService<User> {

    Result login(UserDto userDto);

    Result uploadAvatar(MultipartFile file, Long id);

    Result register(UserDto userDto);
}
