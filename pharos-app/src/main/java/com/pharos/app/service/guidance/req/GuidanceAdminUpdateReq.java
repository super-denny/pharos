package com.pharos.app.service.guidance.req;

import com.pharos.common.query.BaseQuery;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 6:07 PM
 */
@Data
public class GuidanceAdminUpdateReq {
    private Integer id;

    @NotBlank(message = "请填写标题")
    private String title;

    private String icon;

    @NotBlank(message = "请填写URL")
    private String url;

    @NotNull(message = "请选择类型")
    private Integer type;
}

