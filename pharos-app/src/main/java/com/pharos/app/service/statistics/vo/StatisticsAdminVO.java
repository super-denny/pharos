package com.pharos.app.service.statistics.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/11/16 4:42 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsAdminVO {

    private Long total;

    private Long yesterday;

    private Long today;

    private Integer sceneValue;
}
