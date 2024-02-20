package com.pharos.app.service.guidance;

import com.github.pagehelper.PageInfo;
import com.pharos.app.manager.GuidanceCacheManager;
import com.pharos.app.service.guidance.req.GuidanceAdminReq;
import com.pharos.app.service.guidance.req.GuidanceAdminUpdateReq;
import com.pharos.app.service.guidance.vo.GuidanceVO;
import com.pharos.common.enums.DeleteTagEnum;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.guidance.GuidanceGateway;
import com.pharos.domain.guidance.GuidanceTypeGateway;
import com.pharos.domain.guidance.dto.GuidanceDTO;
import com.pharos.domain.guidance.dto.GuidanceTypeDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 5:55 PM
 */
@Service
public class GuidanceAdminService {

    @Resource
    private GuidanceGateway guidanceGateway;

    @Resource
    private GuidanceTypeGateway guidanceTypeGateway;

    @Resource
    private GuidanceCacheManager guidanceCacheManager;

    private static final String DEFAULT_ICON = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80";


    public PageInfo<GuidanceDTO> list(GuidanceAdminReq guidanceAdminReq) {
        GuidanceDTO guidanceTypeDTO = OrikaMapperUtils.map(guidanceAdminReq, GuidanceDTO.class);
        PageInfo<GuidanceDTO> guidanceDTOPageInfo = guidanceGateway.pageList(guidanceTypeDTO);
        List<GuidanceDTO> list = guidanceDTOPageInfo.getList();
        if (CollectionUtils.isEmpty(list)) {
            return guidanceDTOPageInfo;
        }
        List<Integer> typeIds = list.stream().map(GuidanceDTO::getType).collect(Collectors.toList());
        List<GuidanceTypeDTO> typeDTOList = guidanceTypeGateway.queryByIds(typeIds);
        if (CollectionUtils.isEmpty(typeDTOList)) {
            return guidanceDTOPageInfo;
        }
        Map<Integer, String> typeMap = typeDTOList.stream().collect(Collectors.toMap(GuidanceTypeDTO::getId, GuidanceTypeDTO::getTitle, (a, b) -> b));
        list.forEach(x -> {
            String type = typeMap.get(x.getType());
            x.setTypeName(type);
        });
        guidanceDTOPageInfo.setList(list);
        return guidanceDTOPageInfo;
    }

    public Long totalLink() {
        return guidanceGateway.totalLink();
    }

    public void update(GuidanceAdminUpdateReq guidanceAdminUpdateReq) {
        GuidanceDTO guidanceTypeDTO = OrikaMapperUtils.map(guidanceAdminUpdateReq, GuidanceDTO.class);
        Integer id = guidanceAdminUpdateReq.getId();
        String icon = guidanceTypeDTO.getIcon();
        if (StringUtils.isBlank(icon)) {
            guidanceTypeDTO.setIcon(DEFAULT_ICON);
        }
        if (Objects.nonNull(id)) {
            guidanceGateway.update(guidanceTypeDTO);
        } else {
            guidanceGateway.insert(guidanceTypeDTO);
        }
        guidanceCacheManager.buildGuidanceCache();
    }

    public GuidanceVO detail(Integer id) {
        List<GuidanceDTO> guidanceDTOS = guidanceGateway.queryByIds(Collections.singletonList(id));
        if (CollectionUtils.isEmpty(guidanceDTOS)) {
            return null;
        }
        GuidanceVO guidanceVO = OrikaMapperUtils.map(guidanceDTOS.get(0), GuidanceVO.class);
        List<GuidanceTypeDTO> typeDTOList = guidanceTypeGateway.queryByIds(Collections.singletonList(guidanceDTOS.get(0).getType()));
        if (CollectionUtils.isEmpty(typeDTOList)) {
            return guidanceVO;
        }
        guidanceVO.setTypeName(typeDTOList.get(0).getTitle());
        return guidanceVO;
    }

    public void delete(Integer id) {
        GuidanceDTO dto = new GuidanceDTO();
        dto.setId(id);
        dto.setDeleteTag(DeleteTagEnum.DELETED.getStatus());
        guidanceGateway.update(dto);
        guidanceCacheManager.buildGuidanceCache();
    }
}
