package com.pharos.app.service.link.req;

import com.pharos.common.query.BaseQuery;
import lombok.Data;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/19 10:18 PM
 */
@Data
public class LinkInfoReq extends BaseQuery {

    private String title;

    private Integer status;

}
