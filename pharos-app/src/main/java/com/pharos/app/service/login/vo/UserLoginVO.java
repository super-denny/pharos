package com.pharos.app.service.login.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/7 9:53 AM
 */
@Data
@Builder
public class UserLoginVO {

    private String token;

    private String username;

    private String chineseName;

    private String avatar;
}
