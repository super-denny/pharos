package com.pharos.app.service.link.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/18 11:40 AM
 */
@Data
public class LinkSubmitReq {

    @NotNull(message = "请输入标题")
    private String title;

    @NotNull(message = "请输入链接")
    private String url;

    @NotNull(message = "请上传图标")
    private String icon;

    @NotNull(message = "请选择类型")
    private Integer privacy;

    private Integer type;
}
