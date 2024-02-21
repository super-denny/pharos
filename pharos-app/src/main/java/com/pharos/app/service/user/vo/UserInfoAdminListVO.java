package com.pharos.app.service.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/11/24 2:32 PM
 */
@Data
public class UserInfoAdminListVO {
    private Integer id;

    private String account;

    private String nickname;

    private Integer status;

    private Date gmtLastLogin;

    private Date gmtCreate;

    private Date gmtModify;

}
