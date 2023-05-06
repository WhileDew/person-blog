package com.blogs.barrage.client;

import com.blogs.commonutils.utils.Result;
import org.springframework.stereotype.Component;

/**
 * 熔断处理
 * @author 21380
 */
@Component
public class UserDegradeClient implements UserClient {

    @Override
    public Result getUserById(Long id) {
        return Result.error("获取用户信息出错");
    }
}
