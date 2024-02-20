package com.pharos.infrasturcture.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/8/3 10:36 AM
 */
@Data
public class CollectionInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer urlId;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModify;
}
