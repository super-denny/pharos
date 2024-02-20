package com.pharos.infrasturcture.gatewayimpl.guidance;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.pharos.common.enums.DeleteTagEnum;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.guidance.GuidanceGateway;
import com.pharos.domain.guidance.dto.GuidanceDTO;
import com.pharos.domain.guidance.dto.GuidanceTypeDTO;
import com.pharos.infrasturcture.entity.Guidance;
import com.pharos.infrasturcture.entity.GuidanceType;
import com.pharos.infrasturcture.mapper.GuidanceMapper;
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
public class GuidanceGatewayImpl implements GuidanceGateway {

    @Resource
    private GuidanceMapper guidanceMapper;


    @Override
    public List<GuidanceDTO> all() {
        QueryWrapper<Guidance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_tag", DeleteTagEnum.NORMAL.getStatus()).orderByDesc("`rank`").orderByDesc("gmt_create");
        List<Guidance> list = guidanceMapper.selectList(queryWrapper);
        return OrikaMapperUtils.mapList(list, Guidance.class, GuidanceDTO.class);
    }

    @Override
    public PageInfo<GuidanceDTO> pageList(GuidanceDTO guidanceTypeDTO) {
        QueryWrapper<Guidance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_tag", DeleteTagEnum.NORMAL.getStatus()).like(StringUtils.isNotBlank(guidanceTypeDTO.getTitle()), "title", guidanceTypeDTO.getTitle()).orderByDesc("gmt_create");
        Page<Guidance> page = new Page<>(guidanceTypeDTO.getPageNo(), guidanceTypeDTO.getPageSize());
        Page<Guidance> adminInfoPage = guidanceMapper.selectPage(page, queryWrapper);
        List<Guidance> list = adminInfoPage.getRecords();
        List<GuidanceDTO> result = OrikaMapperUtils.mapList(list, Guidance.class, GuidanceDTO.class);
        PageInfo<GuidanceDTO> pageInfo = new PageInfo<>();
        pageInfo.setList(result);
        pageInfo.setTotal(adminInfoPage.getTotal());
        return pageInfo;
    }

    @Override
    public List<GuidanceDTO> queryByIds(List<Integer> urlIds) {
        List<Guidance> list = guidanceMapper.selectBatchIds(urlIds);
        return OrikaMapperUtils.mapList(list, Guidance.class, GuidanceDTO.class);
    }

    @Override
    public void insert(GuidanceDTO guidanceDTO) {
        Guidance guidance = OrikaMapperUtils.map(guidanceDTO, Guidance.class);
        guidanceMapper.insert(guidance);
    }

    @Override
    public Long totalLink() {
        QueryWrapper<Guidance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_tag", DeleteTagEnum.NORMAL.getStatus());
        return guidanceMapper.selectCount(queryWrapper);
    }

    @Override
    public void update(GuidanceDTO guidanceDTO) {
        Guidance guidance = OrikaMapperUtils.map(guidanceDTO, Guidance.class);
        guidanceMapper.updateById(guidance);
    }

    @Override
    public Long countByTypeId(Integer id) {
        QueryWrapper<Guidance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", id).eq("delete_tag", DeleteTagEnum.NORMAL.getStatus());
        return guidanceMapper.selectCount(queryWrapper);
    }
}
