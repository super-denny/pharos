package com.pharos.app.service.statistics;

import cn.hutool.core.date.DateUtil;
import com.pharos.app.service.statistics.req.ReportReq;
import com.pharos.app.service.statistics.req.StatisticsAdminReq;
import com.pharos.app.service.statistics.vo.StatisticsAdminVO;
import com.pharos.common.utils.RedisUtil;
import com.pharos.domain.statistics.BehavioralStatisticsGateway;
import com.pharos.domain.statistics.constant.BehavioralStatisticsRedisKey;
import com.pharos.domain.statistics.enums.BehavioralStatisticsSceneEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 5:27 PM
 */
@Slf4j
@Service
public class BehavioralStatisticsAdminService {

    @Resource
    private BehavioralStatisticsGateway behavioralStatisticsGateway;

    public StatisticsAdminVO getStatistics(StatisticsAdminReq statisticsAdminReq) {
        Integer sceneValue = statisticsAdminReq.getSceneValue();
        Long total = behavioralStatisticsGateway.getTotalStatisticalByScene(sceneValue);
        Long yesterday = behavioralStatisticsGateway.getStatisticalByData(DateUtil.formatDate(DateUtil.yesterday()), sceneValue);
        Long today = behavioralStatisticsGateway.getStatisticalByData(DateUtil.today(), sceneValue);
        return StatisticsAdminVO.builder().total(total).yesterday(yesterday).today(today).sceneValue(sceneValue).build();
    }
}
