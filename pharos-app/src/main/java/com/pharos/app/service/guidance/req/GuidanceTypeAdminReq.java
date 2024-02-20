package com.pharos.app.service.guidance.req;

import com.pharos.common.query.BaseQuery;
import lombok.Data;

import java.util.Date;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 6:07 PM
 */
@Data
public class GuidanceTypeAdminReq extends BaseQuery {
    private String title;
}
