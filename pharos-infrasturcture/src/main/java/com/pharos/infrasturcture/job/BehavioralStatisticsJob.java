package com.pharos.infrasturcture.job;

import cn.hutool.core.date.DateUtil;
import com.pharos.common.utils.RedisUtil;
import com.pharos.domain.statistics.BehavioralStatisticsGateway;
import com.pharos.domain.statistics.constant.BehavioralStatisticsRedisKey;
import com.pharos.domain.statistics.dto.BehavioralStatisticsDTO;
import com.pharos.domain.statistics.enums.BehavioralStatisticsSceneEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 7:22 PM
 */
@Slf4j
@Component
public class BehavioralStatisticsJob {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private BehavioralStatisticsGateway behavioralStatisticsGateway;


    @Scheduled(cron = "0 0/1 * * * ?")
    public void dataReflow() {
//        log.info("统计数据回流任务正在刷新数据....");
        String dateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
        String websiteAccessKey = String.format(BehavioralStatisticsRedisKey.WEBSITE_ACCESS_KEY, BehavioralStatisticsSceneEnum.WEBSITE_ACCESS.getValue(), dateStr);
        String websiteAccessCountStr = redisUtil.get(websiteAccessKey);
        if (StringUtils.isNotBlank(websiteAccessCountStr)) {
            BehavioralStatisticsDTO statisticsDTO = behavioralStatisticsGateway.getByValueAndDate(BehavioralStatisticsSceneEnum.WEBSITE_ACCESS.getValue(), dateStr);
            if (Objects.isNull(statisticsDTO)) {
                BehavioralStatisticsDTO dto = new BehavioralStatisticsDTO();
                dto.setGmtStatistics(dateStr);
                dto.setSceneValue(BehavioralStatisticsSceneEnum.WEBSITE_ACCESS.getValue());
                dto.setSceneCount(Long.valueOf(websiteAccessCountStr));
                behavioralStatisticsGateway.insert(dto);
            } else if (!Objects.equals(statisticsDTO.getSceneCount(), Long.valueOf(websiteAccessCountStr))) {
                statisticsDTO.setSceneCount(Long.valueOf(websiteAccessCountStr));
                behavioralStatisticsGateway.update(statisticsDTO);
            }
        }
//        log.info("统计数据回流任务正在数据刷新完毕！！！");
    }
}
