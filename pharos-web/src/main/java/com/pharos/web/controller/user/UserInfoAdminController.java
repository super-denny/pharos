package com.pharos.web.controller.user;

import com.github.pagehelper.PageInfo;
import com.pharos.app.service.user.UserInfoAdminService;
import com.pharos.app.service.user.req.UserInfoAdminReq;
import com.pharos.app.service.user.vo.UserInfoAdminListVO;
import com.pharos.common.response.Response;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.user.dto.UserInfoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
