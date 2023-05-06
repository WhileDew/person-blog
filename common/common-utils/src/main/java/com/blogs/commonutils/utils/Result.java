package com.blogs.commonutils.utils;

import lombok.Data;

/**
 * 返回结果实体类
 * @author 21380
 */
@Data
public class Result {
    //编码：1成功，0和其它数字为失败
    private Integer code;
    //错误信息
    private String msg;
    //数据
    private Object data;

    public static Result success(Object object) {
        Result r = new Result();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static Result error(String msg) {
        Result r = new Result();
        r.msg = msg;
        r.code = 0;
        return r;
    }
}
