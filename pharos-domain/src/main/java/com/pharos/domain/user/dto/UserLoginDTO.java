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
    private String dispName;
    private String email;
    private String id;
    private Boolean isActive;
    private Boolean isBoss;
    private Boolean isLeader;
    private String mobile;
    private String position;
    private String userType;
    private String username;
    private String wxId;
}
