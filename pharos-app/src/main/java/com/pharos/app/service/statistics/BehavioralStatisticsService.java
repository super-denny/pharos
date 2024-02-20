package com.pharos.app.service.statistics;

import cn.hutool.core.date.DateUtil;
import com.pharos.app.service.statistics.req.ReportReq;
import com.pharos.common.utils.RedisUtil;
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
public class BehavioralStatisticsService {

    @Resource
    private RedisUtil redisUtil;

    public void report(ReportReq reportReq) {
        Integer sceneValue = reportReq.getSceneValue();
        BehavioralStatisticsSceneEnum sceneEnum = BehavioralStatisticsSceneEnum.getByValue(sceneValue);
        if (Objects.isNull(sceneEnum)) {
            log.error("行为统计场景值不存在，value={}", sceneValue);
            return;
        }
        String dateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
        String websiteAccessKey = String.format(BehavioralStatisticsRedisKey.WEBSITE_ACCESS_KEY, sceneValue, dateStr);
        redisUtil.increment(websiteAccessKey);
    }
}
