package com.pharos.infrasturcture.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/18 2:53 PM
 */
@Data
public class UserInfo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;

    private String nickname;

    private Integer status;

    private String password;
    
    private String salt;

    private Integer encryptTimes;

    private Integer deleteTag;

    private Date gmtLastLogin;

    private Date gmtCreate;

    private Date gmtModify;
}
