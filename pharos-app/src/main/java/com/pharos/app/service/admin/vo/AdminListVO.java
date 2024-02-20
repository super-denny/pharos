package com.pharos.app.service.admin.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/7 4:47 PM
 */
@Data
public class AdminListVO {
    private Integer id;
    private String name;
    private String avatar;
    private String account;
    private Integer type;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModify;
}
