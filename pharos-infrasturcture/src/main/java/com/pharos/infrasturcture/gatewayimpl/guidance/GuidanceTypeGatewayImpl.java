package com.pharos.infrasturcture.gatewayimpl.guidance;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.pharos.common.enums.DeleteTagEnum;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.admin.dto.AdminInfoDTO;
import com.pharos.domain.guidance.GuidanceTypeGateway;
import com.pharos.domain.guidance.dto.GuidanceTypeDTO;
import com.pharos.infrasturcture.entity.AdminInfo;
import com.pharos.infrasturcture.entity.GuidanceType;
import com.pharos.infrasturcture.mapper.GuidanceMapper;
import com.pharos.infrasturcture.mapper.GuidanceTypeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 5:52 PM
 */
@Service
public class GuidanceTypeGatewayImpl implements GuidanceTypeGateway {

    @Resource
    private GuidanceTypeMapper guidanceTypeMapper;

    @Override
    public List<GuidanceTypeDTO> all() {
        QueryWrapper<GuidanceType> queryWrapper = new QueryWrapper<>();
        List<GuidanceType> list = guidanceTypeMapper.selectList(queryWrapper);
        return OrikaMapperUtils.mapList(list, GuidanceType.class, GuidanceTypeDTO.class);
    }

    @Override
    public PageInfo<GuidanceTypeDTO> pageList(GuidanceTypeDTO guidanceTypeDTO) {
        QueryWrapper<GuidanceType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_tag", DeleteTagEnum.NORMAL.getStatus()).like(StringUtils.isNotBlank(guidanceTypeDTO.getTitle()), "title", guidanceTypeDTO.getTitle()).orderByDesc("id");
        Page<GuidanceType> page = new Page<>(guidanceTypeDTO.getPageNo(), guidanceTypeDTO.getPageSize());
        Page<GuidanceType> adminInfoPage = guidanceTypeMapper.selectPage(page, queryWrapper);
        List<GuidanceType> list = adminInfoPage.getRecords();
        List<GuidanceTypeDTO> result = OrikaMapperUtils.mapList(list, GuidanceType.class, GuidanceTypeDTO.class);
        PageInfo<GuidanceTypeDTO> pageInfo = new PageInfo<>();
        pageInfo.setList(result);
        pageInfo.setTotal(adminInfoPage.getTotal());
        return pageInfo;
    }

    @Override
    public void update(GuidanceTypeDTO guidanceTypeDTO) {
        guidanceTypeMapper.updateById(OrikaMapperUtils.map(guidanceTypeDTO, GuidanceType.class));
    }

    @Override
    public void insert(GuidanceTypeDTO guidanceTypeDTO) {
        guidanceTypeMapper.insert(OrikaMapperUtils.map(guidanceTypeDTO, GuidanceType.class));
    }

    @Override
    public GuidanceTypeDTO detail(Integer id) {
        GuidanceType guidanceType = guidanceTypeMapper.selectById(id);
        return OrikaMapperUtils.map(guidanceType, GuidanceTypeDTO.class);
    }

    @Override
    public List<GuidanceTypeDTO> queryByIds(List<Integer> typeIds) {
        List<GuidanceType> guidanceTypes = guidanceTypeMapper.selectBatchIds(typeIds);
        return OrikaMapperUtils.mapList(guidanceTypes, GuidanceType.class, GuidanceTypeDTO.class);
    }
}
