package com.pharos.app.service.admin.req;

import com.pharos.common.query.BaseQuery;
import lombok.Data;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/7 4:48 PM
 */
@Data
public class AdminListReq extends BaseQuery {

    private String name;

    private String account;

}
