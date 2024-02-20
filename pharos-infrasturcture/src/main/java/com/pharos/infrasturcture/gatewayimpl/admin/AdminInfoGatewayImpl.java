package com.pharos.infrasturcture.gatewayimpl.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.pharos.common.enums.DeleteTagEnum;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.admin.AdminInfoGateway;
import com.pharos.domain.admin.dto.AdminInfoDTO;
import com.pharos.infrasturcture.entity.AdminInfo;
import com.pharos.infrasturcture.mapper.AdminInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/4 11:06 AM
 */
@Service
public class AdminInfoGatewayImpl implements AdminInfoGateway {

    @Resource
    private AdminInfoMapper adminInfoMapper;

    @Override
    public AdminInfoDTO getByAccount(String account) {
        QueryWrapper<AdminInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account).eq("delete_tag", DeleteTagEnum.NORMAL.getDesc());
        AdminInfo adminInfo = adminInfoMapper.selectOne(queryWrapper);
        return OrikaMapperUtils.map(adminInfo, AdminInfoDTO.class);
    }

    @Override
    public PageInfo<AdminInfoDTO> list(AdminInfoDTO adminInfoDTO) {
        QueryWrapper<AdminInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_tag", DeleteTagEnum.NORMAL.getStatus())
                .like(StringUtils.isNotBlank(adminInfoDTO.getName()), "name", adminInfoDTO.getName())
                .like(StringUtils.isNotBlank(adminInfoDTO.getAccount()), "account", adminInfoDTO.getAccount())
                .orderByDesc("gmt_create");
        Page<AdminInfo> page = new Page<>(adminInfoDTO.getPageNo(), adminInfoDTO.getPageSize());
        Page<AdminInfo> adminInfoPage = adminInfoMapper.selectPage(page, queryWrapper);
        List<AdminInfo> list = adminInfoPage.getRecords();
        List<AdminInfoDTO> result = OrikaMapperUtils.mapList(list, AdminInfo.class, AdminInfoDTO.class);
        PageInfo<AdminInfoDTO> pageInfo = new PageInfo<>();
        pageInfo.setList(result);
        pageInfo.setTotal(adminInfoPage.getTotal());
        return pageInfo;
    }

    @Override
    public void add(AdminInfoDTO adminInfoDTO) {
        AdminInfo adminInfo = OrikaMapperUtils.map(adminInfoDTO, AdminInfo.class);
        adminInfoMapper.insert(adminInfo);
    }

    @Override
    public void update(AdminInfoDTO adminInfoDTO) {
        AdminInfo adminInfo = OrikaMapperUtils.map(adminInfoDTO, AdminInfo.class);
        adminInfoMapper.updateById(adminInfo);
    }

    @Override
    public AdminInfoDTO detail(Integer id) {
        QueryWrapper<AdminInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("delete_tag", DeleteTagEnum.NORMAL.getStatus());
        AdminInfo adminInfo = adminInfoMapper.selectOne(queryWrapper);
        return OrikaMapperUtils.map(adminInfo, AdminInfoDTO.class);
    }
}
