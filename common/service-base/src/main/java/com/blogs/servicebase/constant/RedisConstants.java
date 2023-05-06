package com.blogs.servicebase.constant;

import java.util.concurrent.TimeUnit;

/**
 * redis键常量
 * @author 21380
 */
public class RedisConstants {

    public static final String LOGIN_CODE_KEY = "login_code_";
    public static final Long LOGIN_CODE_KEY_EXPIRE = 3L;
    public static final TimeUnit LOGIN_CODE_KEY_TIMEUNIT = TimeUnit.MINUTES;
}
