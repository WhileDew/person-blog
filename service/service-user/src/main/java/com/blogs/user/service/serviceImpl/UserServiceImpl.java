package com.blogs.user.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogs.commonutils.utils.JwtUtils;
import com.blogs.servicebase.constant.RedisConstants;
import com.blogs.user.dto.UserDto;
import com.blogs.user.entity.User;
import com.blogs.user.mapper.UserMapper;
import com.blogs.user.property.AliyunOssProperties;
import com.blogs.commonutils.utils.Result;
import com.blogs.user.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 21380
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(UserDto userDto) {
        // 判空
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        // 账号或密码为空
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            return Result.error("账号密码不能为空");
        }
        // 查询数据库中是否有该用户
        User user = query().eq("email", email).one();
        if (user == null) {
            return Result.error("用户不存在");
        }
        // 判断密码是否正确
        if (!password.equals(DigestUtil.bcrypt(user.getPassword()))) {
            return Result.error("密码错误");
        }
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("username", user.getUsername());
            put("avatar", user.getAvatar());
            put("email", user.getEmail());
        }};
        // 生成token字符串
        return Result.success(JwtUtils.createToken(map));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result uploadAvatar(MultipartFile file, Long id) {
        // 上传头像
        String endpoint = AliyunOssProperties.END_POINT;
        String accessKeyId = AliyunOssProperties.ACCESS_KEY_ID;
        String accessKeySecret = AliyunOssProperties.ACCESS_KEY_SECRET;
        String bucketName = AliyunOssProperties.BUCKET_NAME;

        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            // 获取文件名称
            String fileName = file.getOriginalFilename();

            //b在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuid+fileName;

            ossClient.putObject(bucketName, fileName , inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            // 把上传之后文件路径返回
            // 需要把上传到阿里云oss路径手动拼接出来
            //  https://edu-guli-1010.oss-cn-beijing.aliyuncs.com/01.jpg
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            // 保存到用户信息中
            User user = getById(id);
            user.setAvatar(url);
            updateById(user);
            Map<String, Object> map = new HashMap<String, Object>(4){{
                put("id", user.getId());
                put("username", user.getUsername());
                put("avatar", user.getAvatar());
                put("email", user.getEmail());
            }};
            return Result.success(JwtUtils.createToken(map));
        }catch(Exception e) {
            e.printStackTrace();
            return Result.error("上传失败，请重试");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result register(UserDto userDto) {
        // 判空
        if (BeanUtil.hasNullField(userDto, "avatar")) {
            return Result.error("用户信息不完整");
        }
        String email = userDto.getEmail();
        String code = userDto.getCode();
        String s = stringRedisTemplate.opsForValue().get(RedisConstants.LOGIN_CODE_KEY + email);
        // 判断是否已经注册过
        if (query().eq("email", email).one() != null) {
            return Result.error("该邮箱已经注册过了");
        }
        // 判断验证码是否正确
        if (StringUtils.isBlank(s) || !s.equals(code)) {
            return Result.error("验证码错误");
        }
        // 将密码进行MD5加密
        userDto.setPassword(DigestUtil.bcrypt(userDto.getPassword()));
        User user = BeanUtil.copyProperties(userDto, User.class, "code");
        boolean success = save(user);
        System.out.println(user);
        if (!success) {
            return Result.error("服务器异常，稍后重试");
        }
        Map<String, Object> map = new HashMap<String, Object>(4){{
            put("id", user.getId());
            put("username", userDto.getUsername());
            put("avatar", userDto.getAvatar());
            put("email", userDto.getEmail());
        }};
        // 生成token字符串
        return Result.success(JwtUtils.createToken(map));
    }
}
