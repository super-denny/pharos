package com.pharos.app.service.guidance.req;

import com.pharos.common.query.BaseQuery;
import lombok.Data;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 6:07 PM
 */
@Data
public class GuidanceAdminReq extends BaseQuery {
    private String title;
}
