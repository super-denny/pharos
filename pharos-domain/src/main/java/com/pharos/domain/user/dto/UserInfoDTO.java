package com.pharos.domain.user.dto;

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
public class UserInfoDTO {

    private Integer id;

    private String username;

    private String dispName;

    private Integer status;

    private Date gmtLastLogin;

    private Date gmtCreate;

    private Date gmtModify;

    private Integer pageNo;
    private Integer pageSize;
}
