package com.pharos.infrasturcture.gatewayimpl.link;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.link.LinkInfoGateway;
import com.pharos.domain.link.dto.LinkInfoDTO;
import com.pharos.domain.link.enums.LinkPrivacyEnum;
import com.pharos.domain.link.enums.LinkStatusEnum;
import com.pharos.infrasturcture.entity.LinkInfo;
import com.pharos.infrasturcture.mapper.LinkInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/19 7:15 PM
 */
@Service
public class LinkInfoGatewayImpl implements LinkInfoGateway {

    @Resource
    private LinkInfoMapper linkInfoMapper;


    @Override
    public void insert(LinkInfoDTO linkInfoDTO) {
        linkInfoMapper.insert(OrikaMapperUtils.map(linkInfoDTO, LinkInfo.class));
    }

    @Override
    public PageInfo<LinkInfoDTO> pageList(LinkInfoDTO linkInfoDTO) {
        QueryWrapper<LinkInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(linkInfoDTO.getTitle()), "title", linkInfoDTO.getTitle())
                .eq(Objects.nonNull(linkInfoDTO.getStatus()), "status", linkInfoDTO.getStatus())
                .orderByDesc("gmt_create");
        Page<LinkInfo> page = new Page<>(linkInfoDTO.getPageNo(), linkInfoDTO.getPageSize());
        Page<LinkInfo> adminInfoPage = linkInfoMapper.selectPage(page, queryWrapper);
        List<LinkInfo> list = adminInfoPage.getRecords();
        List<LinkInfoDTO> result = OrikaMapperUtils.mapList(list, LinkInfo.class, LinkInfoDTO.class);
        PageInfo<LinkInfoDTO> pageInfo = new PageInfo<>();
        pageInfo.setList(result);
        pageInfo.setTotal(adminInfoPage.getTotal());
        return pageInfo;
    }

    @Override
    public void update(LinkInfoDTO linkInfoDTO) {
        linkInfoMapper.updateById(OrikaMapperUtils.map(linkInfoDTO, LinkInfo.class));
    }

    @Override
    public List<LinkInfoDTO> getSubmitList(Integer userId) {
        QueryWrapper<LinkInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).orderByDesc("gmt_create");
        List<LinkInfo> linkInfos = linkInfoMapper.selectList(queryWrapper);
        return OrikaMapperUtils.mapList(linkInfos, LinkInfo.class, LinkInfoDTO.class);
    }

    @Override
    public LinkInfoDTO getById(Integer id) {
        LinkInfo linkInfo = linkInfoMapper.selectById(id);
        return OrikaMapperUtils.map(linkInfo, LinkInfoDTO.class);
    }

    @Override
    public List<LinkInfoDTO> queryPrivacyList(Integer userId) {
        QueryWrapper<LinkInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("privacy", LinkPrivacyEnum.PRIVACY.getStatus())
                .eq("status", LinkStatusEnum.PASS.getStatus())
                .orderByDesc("gmt_create");
        List<LinkInfo> linkInfos = linkInfoMapper.selectList(queryWrapper);
        return OrikaMapperUtils.mapList(linkInfos, LinkInfo.class, LinkInfoDTO.class);
    }
}
