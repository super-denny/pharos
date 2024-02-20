package com.pharos.infrasturcture.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/19 7:13 PM
 */
@Data
public class LinkInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String icon;
    private String url;
    private Integer type;
    private Integer userId;
    private Integer adminId;
    private Integer privacy;
    private Integer status;
    private String failReason;
    private Date gmtCreate;
    private Date gmtModify;
}
