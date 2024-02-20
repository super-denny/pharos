package com.pharos.web.controller.statistics;

import com.pharos.app.service.statistics.BehavioralStatisticsService;
import com.pharos.app.service.statistics.req.ReportReq;
import com.pharos.common.annotation.NoLogin;
import com.pharos.common.response.Response;
import com.pharos.web.controller.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 5:26 PM
 */
@RestController
@RequestMapping("/api/bs")
public class BehavioralStatisticsController extends BaseController {

    @Resource
    private BehavioralStatisticsService behavioralStatisticsService;

    @NoLogin
    @PostMapping("/report")
    public Response<Void> report(@RequestBody ReportReq reportReq) {
        behavioralStatisticsService.report(reportReq);
        return new Response<Void>().success();
    }

}
