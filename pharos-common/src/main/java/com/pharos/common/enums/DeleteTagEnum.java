package com.pharos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/11 3:16 PM
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DeleteTagEnum {

    NORMAL(0, "正常"),
    DELETED(1, "已删除");

    private Integer status;

    private String desc;

}
