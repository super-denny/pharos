package com.pharos.web.controller.guidance;

import com.pharos.app.service.guidance.GuidanceTypeService;
import com.pharos.app.service.guidance.vo.GuidanceTypeVO;
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
 * @date 2023/7/4 2:12 PM
 */
@RestController
@RequestMapping("/api/guidanceType")
public class GuidanceTypeController extends BaseController {

    @Resource
    private GuidanceTypeService guidanceTypeService;

    @NoLogin
    @GetMapping("/all")
    public Response<List<GuidanceTypeVO>> all() {
        return new Response<List<GuidanceTypeVO>>().success(guidanceTypeService.all());
    }
}
