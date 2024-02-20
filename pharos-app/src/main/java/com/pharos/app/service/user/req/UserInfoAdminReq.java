package com.pharos.app.service.user.req;

import com.pharos.common.query.BaseQuery;
import lombok.Data;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/11/24 2:32 PM
 */
@Data
public class UserInfoAdminReq extends BaseQuery {

    private String username;

    private String dispName;

    private Integer id;

    private Integer status;
}
