package com.pharos.web.controller.link;

import com.github.pagehelper.PageInfo;
import com.pharos.app.service.guidance.req.GuidanceAdminReq;
import com.pharos.app.service.guidance.vo.GuidanceVO;
import com.pharos.app.service.link.LinkInfoAdminService;
import com.pharos.app.service.link.req.LinkAuditReq;
import com.pharos.app.service.link.req.LinkInfoReq;
import com.pharos.app.service.link.req.LinkSubmitReq;
import com.pharos.app.service.link.vo.LinkInfoVO;
import com.pharos.common.annotation.NoLogin;
import com.pharos.common.response.Response;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.guidance.dto.GuidanceDTO;
import com.pharos.domain.link.dto.LinkInfoDTO;
import com.pharos.web.controller.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/18 11:35 AM
 */
@RestController
@RequestMapping("/admin/link")
public class LinkInfoAdminController extends BaseController {

    @Resource
    private LinkInfoAdminService linkInfoAdminService;

    @PostMapping("/list")
    @NoLogin
    public Response<PageInfo<LinkInfoVO>> list(@RequestBody LinkInfoReq req) {
        PageInfo<LinkInfoDTO> list = linkInfoAdminService.list(req);
        PageInfo<LinkInfoVO> result = new PageInfo<>(OrikaMapperUtils.mapList(list.getList(), LinkInfoDTO.class, LinkInfoVO.class));
        result.setTotal(list.getTotal());
        return new Response<PageInfo<LinkInfoVO>>().success(result);
    }

    @PostMapping("/audit")
    public Response<Void> audit(@Valid @RequestBody LinkAuditReq req) {
        linkInfoAdminService.audit(req, getLoginAdminId());
        return new Response<Void>().success();
    }

}
