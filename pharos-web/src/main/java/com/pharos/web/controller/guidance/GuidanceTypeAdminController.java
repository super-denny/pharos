package com.pharos.web.controller.guidance;

import com.github.pagehelper.PageInfo;
import com.pharos.app.service.admin.vo.AdminListVO;
import com.pharos.app.service.guidance.GuidanceTypeAdminService;
import com.pharos.app.service.guidance.req.GuidanceTypeAdminReq;
import com.pharos.app.service.guidance.req.GuidanceTypeAdminUpdateReq;
import com.pharos.app.service.guidance.vo.GuidanceTypeVO;
import com.pharos.common.annotation.NoLogin;
import com.pharos.common.response.Response;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.admin.dto.AdminInfoDTO;
import com.pharos.domain.guidance.dto.GuidanceTypeDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/10 7:09 PM
 */
@RestController
@RequestMapping("/admin/guidanceType")
public class GuidanceTypeAdminController {
    @Resource
    private GuidanceTypeAdminService guidanceTypeAdminService;

    @PostMapping("/list")
    public Response<PageInfo<GuidanceTypeVO>> list(@RequestBody GuidanceTypeAdminReq guidanceTypeAdminReq) {
        PageInfo<GuidanceTypeDTO> list = guidanceTypeAdminService.list(guidanceTypeAdminReq);
        PageInfo<GuidanceTypeVO> result = new PageInfo<>(OrikaMapperUtils.mapList(list.getList(), GuidanceTypeDTO.class, GuidanceTypeVO.class));
        result.setTotal(list.getTotal());
        return new Response<PageInfo<GuidanceTypeVO>>().success(result);
    }

    @NoLogin
    @PostMapping("/update")
    public Response<Void> update(@Valid @RequestBody GuidanceTypeAdminUpdateReq guidanceTypeAdminUpdateReq) {
        guidanceTypeAdminService.update(guidanceTypeAdminUpdateReq);
        return new Response<Void>().success();
    }

    @NoLogin
    @GetMapping("/delete")
    public Response<Void> delete(@RequestParam("id") Integer id) {
        guidanceTypeAdminService.delete(id);
        return new Response<Void>().success();
    }


    @NoLogin
    @GetMapping("/detail")
    public Response<GuidanceTypeVO> detail(@RequestParam("id") Integer id) {
        GuidanceTypeDTO dto = guidanceTypeAdminService.detail(id);
        return new Response<GuidanceTypeVO>().success(OrikaMapperUtils.map(dto, GuidanceTypeVO.class));
    }

    @NoLogin
    @GetMapping("/all")
    public Response<List<GuidanceTypeVO>> all() {
        return new Response<List<GuidanceTypeVO>>().success(guidanceTypeAdminService.all());
    }
}
