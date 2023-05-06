package com.blogs.barrage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blogs.barrage.dto.BarrageDto;
import com.blogs.barrage.entity.Barrage;
import com.blogs.commonutils.utils.Result;

/**
 * @author 21380
 */
public interface BarrageService extends IService<Barrage> {
    Result sendBarrage(BarrageDto barrageDto);
}
