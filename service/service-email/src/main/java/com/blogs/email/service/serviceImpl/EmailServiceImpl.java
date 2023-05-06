package com.blogs.email.service.serviceImpl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.blogs.commonutils.utils.Result;
import com.blogs.email.service.EmailService;
import com.blogs.servicebase.constant.RedisConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 邮箱发送接口实现类
 *
 * @author zhuhuix
 * @date 2021-07-19
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EmailServiceImpl implements EmailService {

    @Value("${mail.emailAddr}")
    private String emailAddr;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;
    @Value("${mail.subject}")
    private String subject;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result send(String email) {
        // 读取邮箱配置
        if (email == null || host == null || port == null || username == null || password == null) {
            log.error("信息不完整:" + email);
            return Result.error("信息不完整");
        }
        log.info("发送成功" + email);
        // 设置发送账户信息
        MailAccount account = new MailAccount();
        account.setHost(host);
        account.setPort(Integer.parseInt(port));
        // 设置发送人邮箱
        account.setFrom(emailAddr);
        // 设置发送人名称
        account.setUser(username);
        // 设置发送授权码
        account.setPass(password);
        account.setAuth(true);
        // ssl方式发送
        account.setSslEnable(true);
        // 使用安全连接
        account.setStarttlsEnable(true);
        String code = RandomUtil.randomNumbers(6);
        // 发送邮件
        try {
            MailUtil.send(account, email, subject, code, false, null);
            // 将验证码缓存进redis,过期时间三分钟
            stringRedisTemplate.opsForValue().set(RedisConstants.LOGIN_CODE_KEY + email,
                    code, RedisConstants.LOGIN_CODE_KEY_EXPIRE, RedisConstants.LOGIN_CODE_KEY_TIMEUNIT);
        } catch (Exception e) {
            log.error("发送失败");
            return Result.error("发送失败，请重试");
        }
        return Result.success("发送成功");
    }
}


