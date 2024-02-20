package com.pharos.app.service.admin.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/4 11:06 AM
 */
@Data
public class AdminUpdateReq {

    private Integer id;

    private String avatar;

    @NotBlank(message = "请输入用户名")
    private String name;

    @NotBlank(message = "请输入账号")
    private String account;

    @NotNull(message = "请选择管理员类型")
    private Integer type;
}
