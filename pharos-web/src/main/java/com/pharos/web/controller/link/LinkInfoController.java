package com.pharos.web.controller.link;

import com.pharos.app.service.link.LinkInfoService;
import com.pharos.app.service.link.req.LinkSubmitReq;
import com.pharos.app.service.link.vo.LinkInfoVO;
import com.pharos.app.service.link.vo.LinkSubmitListVO;
import com.pharos.common.response.Response;
import com.pharos.web.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/18 11:35 AM
 */
@RestController
@RequestMapping("/api/link")
public class LinkInfoController extends BaseController {

    @Resource
    private LinkInfoService linkInfoService;

    @PostMapping("/submit")
    public Response<Void> submit(@Valid @RequestBody LinkSubmitReq req) {
        linkInfoService.submit(req, getLoginUserId());
        return new Response<Void>().success();
    }

    @GetMapping("/getSubmitList")
    public Response<LinkSubmitListVO> getSubmitList() {
        LinkSubmitListVO list = linkInfoService.getSubmitList(getLoginUserId());
        return new Response<LinkSubmitListVO>().success(list);
    }

}
