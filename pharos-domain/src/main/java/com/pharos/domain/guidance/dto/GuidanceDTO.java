package com.pharos.domain.guidance.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 6:07 PM
 */
@Data
public class GuidanceDTO {
    private Integer id;
    private String title;
    private String icon;
    private String url;
    private Integer type;
    private String typeName;
    private Integer status;
    private Integer rank;
    private Integer deleteTag;
    private Date gmtCreate;
    private Date gmtModify;
    private Integer pageNo;
    private Integer pageSize;
}
