package com.pharos.infrasturcture.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/4 11:06 AM
 */
@Data
public class AdminInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String avatar;
    private String account;
    private String password;
    private Integer type;
    private String salt;
    private Integer encryptTimes;
    private Integer status;
    private Integer deleteTag;
    private Date gmtCreate;
    private Date gmtModify;
}
