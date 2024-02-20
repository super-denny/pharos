package com.pharos.infrasturcture.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 5:44 PM
 */
@Data
public class GuidanceType {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String icon;
    private Integer deleteTag;
    private String value;
    private Date gmtCreate;
    private Date gmtModify;
}
