package com.pharos.domain.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/7 10:35 AM
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AdminRedisKeysEnum {

    USER_LOGIN_ID("admin:login:id:%s", 60L * 30, ""),
    USER_LOGIN_TOKEN("admin:login:token:%s", 60L * 30, ""),
    ;

    private String key;

    private Long time;

    private String desc;

}
