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
public enum LinkPrivacyEnum {
    PRIVACY(1, "私密"),
    ORDINARY(0, "普通");

    private Integer status;

    private String desc;

}
