package com.pharos.infrasturcture.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 5:08 PM
 */
@Data
public class BehavioralStatistics {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer sceneValue;
    private Long sceneCount;
    private String attribute;
    private String gmtStatistics;
    private Date gmtCreate;
    private Date gmtModify;
}
