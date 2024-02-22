package com.pharos.domain.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/7 10:42 AM
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AdminTypeEnum {

    NORMAL(1, "普通管理员"), SUPER(2, "超级管理员");

    private Integer type;

    private String desc;
}
