package com.blogs.barrage.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 21380
 */
@Data
public class BarrageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "弹幕内容")
    private String msg;

    @ApiModelProperty(value = "弹幕速度")
    /**
     *  6:fast，9:normal，12:slow
     */
    private Integer time;

    @ApiModelProperty(value = "用户id")
    private Long userId;
}
