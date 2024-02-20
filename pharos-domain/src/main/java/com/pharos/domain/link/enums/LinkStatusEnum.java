package com.pharos.domain.link.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/19 10:01 PM
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LinkStatusEnum {
    WAITING_AUDIT(0, "待审核"),
    PASS(1, "审核通过"),
    REJECT(2, "审核驳回"),
    ;

    private Integer status;

    private String desc;

}
