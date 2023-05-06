package com.blogs.barrage.client;

import com.blogs.commonutils.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * blog远程调用接口
 * @author 21380
 */
@Component
@FeignClient(name = "service-user",fallback = UserDegradeClient.class) //调用的服务名称
public interface UserClient {

    /**
     * 获取用户信息
     * @param id 用户id
     * @return
     */
    @GetMapping("/user/{id}")
    public Result getUserById(@PathVariable("id") Long id);

}
