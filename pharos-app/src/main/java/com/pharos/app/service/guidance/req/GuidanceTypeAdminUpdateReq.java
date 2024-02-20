package com.pharos.app.service.guidance.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 6:07 PM
 */
@Data
public class GuidanceTypeAdminUpdateReq {
    private Integer id;
    @NotBlank(message = "请填写标题")
    private String title;
    @NotBlank(message = "请上传图标")
    private String icon;
}
