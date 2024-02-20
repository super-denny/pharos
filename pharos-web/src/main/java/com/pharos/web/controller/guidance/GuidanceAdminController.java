package com.pharos.web.controller.guidance;

import com.github.pagehelper.PageInfo;
import com.pharos.app.service.guidance.GuidanceAdminService;
import com.pharos.app.service.guidance.GuidanceTypeAdminService;
import com.pharos.app.service.guidance.req.GuidanceAdminReq;
import com.pharos.app.service.guidance.req.GuidanceAdminUpdateReq;
import com.pharos.app.service.guidance.req.GuidanceTypeAdminReq;
import com.pharos.app.service.guidance.vo.GuidanceTypeVO;
import com.pharos.app.service.guidance.vo.GuidanceVO;
import com.pharos.common.annotation.NoLogin;
import com.pharos.common.response.Response;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.guidance.dto.GuidanceDTO;
import com.pharos.domain.guidance.dto.GuidanceTypeDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/10 7:09 PM
 */
@RestController
@RequestMapping("/admin/guidance")
public class GuidanceAdminController {
    @Resource
    private GuidanceAdminService guidanceAdminService;

    @PostMapping("/list")
    public Response<PageInfo<GuidanceVO>> list(@RequestBody GuidanceAdminReq guidanceAdminReq) {
        PageInfo<GuidanceDTO> list = guidanceAdminService.list(guidanceAdminReq);
        PageInfo<GuidanceVO> result = new PageInfo<>(OrikaMapperUtils.mapList(list.getList(), GuidanceDTO.class, GuidanceVO.class));
        result.setTotal(list.getTotal());
        return new Response<PageInfo<GuidanceVO>>().success(result);
    }

    @NoLogin
    @PostMapping("/update")
    public Response<Void> update(@Valid @RequestBody GuidanceAdminUpdateReq guidanceAdminUpdateReq) {
        guidanceAdminService.update(guidanceAdminUpdateReq);
        return new Response<Void>().success();
    }

    @GetMapping("/detail")
    public Response<GuidanceVO> detail(@RequestParam("id") Integer id) {
        return new Response<GuidanceVO>().success(guidanceAdminService.detail(id));
    }

    @NoLogin
    @GetMapping("/delete")
    public Response<Void> delete(@RequestParam("id") Integer id) {
        guidanceAdminService.delete(id);
        return new Response<Void>().success();
    }

    @GetMapping("/totalLink")
    public Response<Long> totalLink() {
        Long total = guidanceAdminService.totalLink();
        return new Response<Long>().success(total);
    }


}
