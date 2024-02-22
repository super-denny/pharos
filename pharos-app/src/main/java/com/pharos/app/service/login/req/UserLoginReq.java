package com.pharos.app.service.login.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/7 9:53 AM
 */
@Data
public class UserLoginReq {

    @NotBlank(message = "请输入账号")
    private String account;

    @NotBlank(message = "请输入密码")
    private String password;
}
