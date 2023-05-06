package com.blogs.barrage.controller;

import com.blogs.barrage.dto.BarrageDto;
import com.blogs.barrage.service.BarrageService;
import com.blogs.commonutils.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 21380
 */
@ApiOperation("弹幕控制器")
@RestController
@RequestMapping("/barrage")
public class BarrageController {

    @Resource
    private BarrageService barrageService;

    @ApiOperation("获取所有弹幕")
    @GetMapping("findAll")
    public Result findAll() {
        return Result.success(barrageService.list());
    }

    @ApiOperation("发弹幕")
    @PostMapping("sendBarrage")
    public Result sendBarrage(@RequestBody BarrageDto barrageDto) {
        barrageDto.setUserId(1L);
        return barrageService.sendBarrage(barrageDto);
    }
}
