package com.pharos.infrasturcture.gatewayimpl.statistics;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.statistics.BehavioralStatisticsGateway;
import com.pharos.domain.statistics.dto.BehavioralStatisticsDTO;
import com.pharos.infrasturcture.entity.BehavioralStatistics;
import com.pharos.infrasturcture.mapper.BehavioralStatisticsMapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 5:18 PM
 */
@Service
public class BehavioralStatisticsGatewayImpl implements BehavioralStatisticsGateway {

    @Resource
    private BehavioralStatisticsMapper behavioralStatisticsMapper;


    @Override
    public void insert(BehavioralStatisticsDTO behavioralStatisticsDTO) {
        behavioralStatisticsMapper.insert(OrikaMapperUtils.map(behavioralStatisticsDTO, BehavioralStatistics.class));
    }

    @Override
    public void update(BehavioralStatisticsDTO behavioralStatisticsDTO) {
        behavioralStatisticsMapper.updateById(OrikaMapperUtils.map(behavioralStatisticsDTO, BehavioralStatistics.class));
    }

    @Override
    public BehavioralStatisticsDTO getByValueAndDate(Integer sceneValue, String date) {
        QueryWrapper<BehavioralStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("scene_value", sceneValue).eq("gmt_statistics", date);
        BehavioralStatistics statistics = behavioralStatisticsMapper.selectOne(queryWrapper);
        return OrikaMapperUtils.map(statistics, BehavioralStatisticsDTO.class);
    }

    @Override
    public Long getTotalStatisticalByScene(Integer sceneValue) {
        QueryWrapper<BehavioralStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("sum(scene_count) as sceneCount").eq("scene_value", sceneValue);
        BehavioralStatistics behavioralStatistics = behavioralStatisticsMapper.selectOne(queryWrapper);
        return behavioralStatistics.getSceneCount();
    }

    @Override
    public Long getStatisticalByData(String data, Integer sceneValue) {
        QueryWrapper<BehavioralStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gmt_statistics", data).eq("scene_value", sceneValue);
        BehavioralStatistics behavioralStatistics = behavioralStatisticsMapper.selectOne(queryWrapper);
        if (Objects.isNull(behavioralStatistics)) {
            return NumberUtils.LONG_ZERO;
        }
        return behavioralStatistics.getSceneCount();
    }
}
