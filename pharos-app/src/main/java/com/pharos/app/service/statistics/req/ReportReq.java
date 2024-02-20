package com.pharos.app.service.statistics.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 5:28 PM
 */
@Data
public class ReportReq {

    @NotNull(message = "场景值不能为空")
    private Integer sceneValue;

}
