package com.pharos.web.controller.guidance;

import com.pharos.app.service.guidance.GuidanceService;
import com.pharos.app.service.guidance.vo.GuidanceVO;
import com.pharos.common.annotation.NoLogin;
import com.pharos.common.response.Response;
import com.pharos.web.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 5:56 PM
 */
@RestController
@RequestMapping("/api/guidance")
public class GuidanceController extends BaseController {

    @Resource
    private GuidanceService guidanceService;

    @NoLogin
    @GetMapping("/list")
    public Response<List<GuidanceVO>> list(@RequestParam(required = false) Integer type) {
        return new Response<List<GuidanceVO>>().success(guidanceService.list(type, getLoginUserIdCanNull()));
    }

    @GetMapping("/privacyList")
    public Response<List<GuidanceVO>> privacyList() {
        return new Response<List<GuidanceVO>>().success(guidanceService.privacyList(getLoginUserId()));
    }

}
