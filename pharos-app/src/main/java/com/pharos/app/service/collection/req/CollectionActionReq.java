package com.pharos.app.service.collection.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/8/3 10:51 AM
 */
@Data
public class CollectionActionReq {

    @NotNull(message = "链接不能为空")
    private Integer urlId;

    @NotNull(message = "状态不能为空")
    private Integer status;

}
