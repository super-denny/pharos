package com.pharos.web.controller.admin;

import com.github.pagehelper.PageInfo;
import com.pharos.app.service.admin.AdminInfoService;
import com.pharos.app.service.admin.req.AdminListReq;
import com.pharos.app.service.admin.req.AdminUpdateReq;
import com.pharos.app.service.admin.vo.AdminDetailVO;
import com.pharos.app.service.admin.vo.AdminListVO;
import com.pharos.common.response.Response;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.admin.dto.AdminInfoDTO;
import com.pharos.web.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/7 4:42 PM
 */
@RestController
@RequestMapping("/admin/info")
public class AdminInfoController extends BaseController {

    @Resource
    private AdminInfoService adminInfoService;

    /**
     * 列表
     *
     * @param adminListReq
     * @return
     */
    @PostMapping("/list")
    public Response<PageInfo<AdminListVO>> list(@RequestBody AdminListReq adminListReq) {
        PageInfo<AdminInfoDTO> list = adminInfoService.list(adminListReq);
        PageInfo<AdminListVO> result = new PageInfo<>(OrikaMapperUtils.mapList(list.getList(), AdminInfoDTO.class, AdminListVO.class));
        result.setTotal(list.getTotal());
        return new Response<PageInfo<AdminListVO>>().success(result);
    }

    @PostMapping("/update")
    public Response<Void> update(@Valid @RequestBody AdminUpdateReq adminUpdateReq) {
        adminInfoService.update(adminUpdateReq);
        return new Response<Void>().success();
    }

    @GetMapping("/detail")
    public Response<AdminDetailVO> detail(@RequestParam("id") Integer id) {
        AdminDetailVO detailVO = adminInfoService.detail(id);
        return new Response<AdminDetailVO>().success(detailVO);
    }

    @GetMapping("/delete")
    public Response<Void> delete(@RequestParam("id") Integer id) {
        adminInfoService.delete(id);
        return new Response<Void>().success();
    }

}
