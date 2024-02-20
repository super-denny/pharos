package com.pharos.domain.statistics.dto;

import lombok.Data;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 5:30 PM
 */
@Data
public class BehavioralStatisticsDTO {
    private Long id;
    private Integer sceneValue;
    private Long sceneCount;
    private String attribute;
    private String gmtStatistics;
}
