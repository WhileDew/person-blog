package com.blog;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = BlogsApplicationTests.class)
class BlogsApplicationTests {

    @Test
    void contextLoads() {
        String str = "{id=1, username=光头和低保哪个先来, password=123456, email=2138005605@qq.com, avatar=http://edu-200298.oss-cn-shenzhen.aliyuncs.com/user/22.jpg, createTime=1682713435000, updateTime=1682713503000}";
        System.out.println(JSON.toJSONString("{id=1, username=光头和低保哪个先来, password=123456, email=2138005605@qq.com, avatar=http://edu-200298.oss-cn-shenzhen.aliyuncs.com/user/22.jpg, createTime=1682713435000, updateTime=1682713503000}"));
    }

}
