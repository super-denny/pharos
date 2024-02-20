package com.pharos.web.controller.statistics;

import com.pharos.app.service.statistics.BehavioralStatisticsAdminService;
import com.pharos.app.service.statistics.req.StatisticsAdminReq;
import com.pharos.app.service.statistics.vo.StatisticsAdminVO;
import com.pharos.common.response.Response;
import com.pharos.web.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 5:26 PM
 */
@RestController
@RequestMapping("/admin/bs")
public class BehavioralStatisticsAdminController extends BaseController {

    @Resource
    private BehavioralStatisticsAdminService behavioralStatisticsAdminService;

    @PostMapping("/getStatistics")
    public Response<StatisticsAdminVO> getStatistics(@Valid @RequestBody StatisticsAdminReq statisticsAdminReq) {
        StatisticsAdminVO vo = behavioralStatisticsAdminService.getStatistics(statisticsAdminReq);
        return new Response<StatisticsAdminVO>().success(vo);
    }

}
