package com.pharos.domain.guidance;

import com.github.pagehelper.PageInfo;
import com.pharos.domain.guidance.dto.GuidanceTypeDTO;

import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 5:52 PM
 */
public interface GuidanceTypeGateway {
    List<GuidanceTypeDTO> all();

    PageInfo<GuidanceTypeDTO> pageList(GuidanceTypeDTO guidanceTypeDTO);

    void update(GuidanceTypeDTO guidanceTypeDTO);

    void insert(GuidanceTypeDTO guidanceTypeDTO);

    GuidanceTypeDTO detail(Integer id);

    List<GuidanceTypeDTO> queryByIds(List<Integer> typeIds);
}
