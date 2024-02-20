package com.pharos.app.service.guidance.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 5:57 PM
 */
@Data
public class GuidanceVO {
    private Integer id;
    private String title;
    private String icon;
    private String url;
    private Integer type;
    private String typeName;
    private Integer status;
    private Integer collectionStatus;
    private Date gmtCreate;
    private Date gmtModify;
}
