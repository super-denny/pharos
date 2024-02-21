package com.pharos.domain.user.dto;

import lombok.Data;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/10 2:55 PM
 */
@Data
public class UserLoginDTO {
    private Integer id;
    private String account;
    private String nickname;
}
