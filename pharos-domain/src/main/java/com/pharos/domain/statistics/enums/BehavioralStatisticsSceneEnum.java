package com.pharos.domain.statistics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/19 10:01 PM
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BehavioralStatisticsSceneEnum {
    WEBSITE_ACCESS(1, "网站访问"),
    ;

    private Integer value;

    private String desc;

    public static BehavioralStatisticsSceneEnum getByValue(int value) {
        for (BehavioralStatisticsSceneEnum behavioralStatisticsSceneEnum : BehavioralStatisticsSceneEnum.values()) {
            if (behavioralStatisticsSceneEnum.getValue() == value) {
                return behavioralStatisticsSceneEnum;
            }
        }
        return null;
    }

}
