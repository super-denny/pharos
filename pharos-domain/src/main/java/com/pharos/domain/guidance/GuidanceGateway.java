package com.pharos.domain.guidance;

import com.github.pagehelper.PageInfo;
import com.pharos.domain.guidance.dto.GuidanceDTO;

import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 5:52 PM
 */
public interface GuidanceGateway {
    List<GuidanceDTO> all();

    PageInfo<GuidanceDTO> pageList(GuidanceDTO guidanceTypeDTO);

    List<GuidanceDTO> queryByIds(List<Integer> urlIds);

    void insert(GuidanceDTO guidanceDTO);

    Long totalLink();

    void update(GuidanceDTO guidanceTypeDTO);

    Long countByTypeId(Integer id);
}