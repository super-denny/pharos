package com.pharos.domain.user.enums;

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
public enum UserStatusEnum {

    NORMAL(1, "正常"),
    DEACTIVATE(2, "停用");

    private Integer status;

    private String desc;
}
