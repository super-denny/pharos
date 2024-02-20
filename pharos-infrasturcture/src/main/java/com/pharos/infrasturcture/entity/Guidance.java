package com.pharos.infrasturcture.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Guidance {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String icon;
    private String url;
    private Integer type;
    private Integer status;
    @TableField("`rank`")
    private Integer rank;
    private Integer deleteTag;
    private Date gmtCreate;
    private Date gmtModify;
}
