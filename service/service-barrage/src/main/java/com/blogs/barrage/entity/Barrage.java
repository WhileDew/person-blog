package com.blogs.barrage.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 21380
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_barrage")
@ApiModel(value="Barrage对象", description="弹幕")
public class Barrage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "弹幕id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "弹幕内容")
    private String msg;

    /**
     *  6:fast，9:normal，12:slow
     */
    @ApiModelProperty(value = "弹幕速度")
    private Integer time;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
