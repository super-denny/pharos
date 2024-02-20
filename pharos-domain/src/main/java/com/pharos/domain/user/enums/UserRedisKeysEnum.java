package com.pharos.domain.user.enums;

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
public enum UserRedisKeysEnum {

    USER_LOGIN_ID("user:login:id:%s", 60L * 60 * 24 * 10, ""),
    USER_LOGIN_TOKEN("user:login:token:%s", 60L * 60 * 24 * 10, ""),
    ;

    private String key;

    private Long time;

    private String desc;

}
