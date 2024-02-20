package com.pharos.domain.statistics;

import com.pharos.domain.statistics.dto.BehavioralStatisticsDTO;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 5:18 PM
 */
public interface BehavioralStatisticsGateway {
    void insert(BehavioralStatisticsDTO behavioralStatisticsDTO);

    void update(BehavioralStatisticsDTO behavioralStatisticsDTO);

    BehavioralStatisticsDTO getByValueAndDate(Integer sceneValue, String date);

    Long getTotalStatisticalByScene(Integer sceneValue);

    Long getStatisticalByData(String data, Integer sceneValue);
}
