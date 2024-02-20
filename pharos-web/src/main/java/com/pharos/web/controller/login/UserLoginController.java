package com.pharos.web.controller.login;

import com.pharos.app.service.login.UserLoginService;
import com.pharos.app.service.login.req.UserLoginReq;
import com.pharos.app.service.login.vo.UserLoginVO;
import com.pharos.common.response.Response;
import org.springframework.validation.annotation.Validated;
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
 * @date 2023/7/10 2:20 PM
 */
@RestController
@RequestMapping("/api")
public class UserLoginController {

    @Resource
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public Response<UserLoginVO> login(@RequestBody @Valid UserLoginReq userLoginReq) {
        UserLoginVO vo = userLoginService.login(userLoginReq);
        return new Response<UserLoginVO>().success(vo);
    }


}
