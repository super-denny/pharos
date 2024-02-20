package com.pharos.domain.link.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/19 7:13 PM
 */
@Data
public class LinkInfoDTO {
    private Integer id;
    private String title;
    private String icon;
    private String url;
    private Integer type;
    private Integer userId;
    private Integer privacy;
    private Integer status;
    private Integer adminId;
    private String failReason;
    private String userName;
    private String adminName;
    private Date gmtCreate;
    private Date gmtModify;
    private Integer pageNo;
    private Integer pageSize;
}
