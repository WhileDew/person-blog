package com.blogs.servicebase.handler;

import com.blogs.commonutils.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 21380
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 指定出现什么异常执行这个方法
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error("服务器异常");
    }

}
