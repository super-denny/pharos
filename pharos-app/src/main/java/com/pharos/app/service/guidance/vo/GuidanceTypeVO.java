package com.pharos.app.service.guidance.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 6:07 PM
 */
@Data
public class GuidanceTypeVO {
    private Integer id;
    private String title;
    private String icon;
    private String value;
    private Date gmtCreate;
    private Date gmtModify;
}
