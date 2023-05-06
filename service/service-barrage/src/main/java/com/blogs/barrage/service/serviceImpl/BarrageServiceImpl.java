package com.blogs.barrage.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.blogs.user.entity.User;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogs.barrage.client.UserClient;
import com.blogs.barrage.dto.BarrageDto;
import com.blogs.barrage.entity.Barrage;
import com.blogs.barrage.mapper.BarrageMapper;
import com.blogs.barrage.service.BarrageService;
import com.blogs.commonutils.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 21380
 */
@Service
public class BarrageServiceImpl extends ServiceImpl<BarrageMapper, Barrage> implements BarrageService {

    @Resource
    private UserClient userClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result sendBarrage(BarrageDto barrageDto) {
        // 根据用户id查询用户信息填入弹幕
        Long userId = barrageDto.getUserId();
        if (userId == null) {
            return Result.error("用户id为空");
        }
        // JSON转换为user对象
        User user = JSONUtil.toBean(JSON.toJSONString(userClient.getUserById(userId).getData()), User.class);
        Barrage barrage = BeanUtil.copyProperties(barrageDto, Barrage.class);
        barrage.setAvatar(user.getAvatar());
        if (save(barrage)) {
            return Result.success(user.getAvatar());
        }else {
            return Result.error("服务器异常");
        }
    }
}
