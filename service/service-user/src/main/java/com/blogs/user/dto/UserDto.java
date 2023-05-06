package com.blogs.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 21380
 */
@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    private String code;
}
