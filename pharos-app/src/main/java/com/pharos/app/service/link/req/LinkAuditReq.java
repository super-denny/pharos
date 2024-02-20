package com.pharos.app.service.link.req;

import com.pharos.domain.link.enums.LinkStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/18 11:40 AM
 */
@Data
public class LinkAuditReq {

    @NotNull(message = "参数丢失")
    private Integer id;

    /**
     * @see LinkStatusEnum
     */
    @NotNull(message = "参数丢失")
    private Integer status;

    private String failReason;
}
