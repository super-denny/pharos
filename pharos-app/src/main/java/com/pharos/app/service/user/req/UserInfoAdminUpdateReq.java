package com.pharos.app.service.user.req;

import com.pharos.common.query.BaseQuery;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/11/24 2:32 PM
 */
@Data
public class UserInfoAdminUpdateReq {

    private Integer id;

    @NotBlank(message = "账号不能为空")
    private String account;

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @NotBlank(message = "密码不能为空")
    private String password;
}
