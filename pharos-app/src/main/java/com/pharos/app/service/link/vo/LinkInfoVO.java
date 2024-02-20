package com.pharos.app.service.link.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/19 10:18 PM
 */
@Data
public class LinkInfoVO {
    private Integer id;
    private String title;
    private String icon;
    private String url;
    private Integer privacy;
    private Integer status;
    private String failReason;
    private String userName;
    private String adminName;
    private Date gmtCreate;
    private Date gmtModify;
}
