package com.pharos.web.controller.user;

import com.github.pagehelper.PageInfo;
import com.pharos.app.service.user.UserInfoAdminService;
import com.pharos.app.service.user.req.UserInfoAdminReq;
import com.pharos.app.service.user.req.UserInfoAdminUpdateReq;
import com.pharos.app.service.user.vo.UserInfoAdminListVO;
import com.pharos.common.response.Response;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.user.dto.UserInfoDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/11/24 2:23 PM
 */
@RestController
@RequestMapping("/admin/userInfo")
public class UserInfoAdminController {

    @Resource
    private UserInfoAdminService userInfoAdminService;

    @PostMapping("/update")
    public Response<Void> update(@Valid @RequestBody UserInfoAdminUpdateReq userInfoAdminUpdateReq) {
        userInfoAdminService.update(userInfoAdminUpdateReq);
        return new Response<Void>().success();
    }

    @PostMapping("/list")
    public Response<PageInfo<UserInfoAdminListVO>> list(@RequestBody UserInfoAdminReq userInfoAdminReq) {
        PageInfo<UserInfoDTO> list = userInfoAdminService.list(userInfoAdminReq);
        PageInfo<UserInfoAdminListVO> result = new PageInfo<>(OrikaMapperUtils.mapList(list.getList(), UserInfoDTO.class, UserInfoAdminListVO.class));
        result.setTotal(list.getTotal());
        return new Response<PageInfo<UserInfoAdminListVO>>().success(result);
    }

    @PostMapping("/limitLogin")
    public Response<Void> limitLogin(@RequestBody UserInfoAdminReq userInfoAdminReq) {
        userInfoAdminService.limitLogin(userInfoAdminReq);
        return new Response<Void>().success();
    }

    @GetMapping("/delete")
    public Response<Void> delete(@RequestParam("id") Integer id) {
        userInfoAdminService.delete(id);
        return new Response<Void>().success();
    }

    @GetMapping("/detail")
    public Response<UserInfoAdminListVO> detail(@RequestParam("id") Integer id) {
        UserInfoAdminListVO listVO = userInfoAdminService.detail(id);
        return new Response<UserInfoAdminListVO>().success(listVO);
    }

}
