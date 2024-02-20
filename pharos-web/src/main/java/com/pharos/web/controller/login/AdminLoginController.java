package com.pharos.web.controller.login;

import com.pharos.app.service.login.AdminLoginService;
import com.pharos.app.service.login.req.AdminLoginReq;
import com.pharos.app.service.login.vo.AdminLoginVO;
import com.pharos.common.response.Response;
import com.pharos.web.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/7 9:50 AM
 */
@RestController
@RequestMapping("/admin")
public class AdminLoginController extends BaseController {

    @Resource
    private AdminLoginService adminLoginService;

    @PostMapping("/login")
    public Response<AdminLoginVO> login(@Valid @RequestBody AdminLoginReq adminLoginReq) {
        AdminLoginVO adminLoginVO = adminLoginService.login(adminLoginReq);
        return new Response<AdminLoginVO>().success(adminLoginVO);
    }

    @GetMapping("/logout")
    public Response<Void> logout() {
        adminLoginService.logout(getLoginAdminId());
        return new Response<Void>().success();
    }

}
