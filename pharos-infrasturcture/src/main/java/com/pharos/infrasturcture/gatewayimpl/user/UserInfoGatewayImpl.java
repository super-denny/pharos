package com.pharos.infrasturcture.gatewayimpl.user;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.link.dto.LinkInfoDTO;
import com.pharos.domain.user.UserInfoGateway;
import com.pharos.domain.user.dto.UserInfoDTO;
import com.pharos.infrasturcture.entity.LinkInfo;
import com.pharos.infrasturcture.entity.UserInfo;
import com.pharos.infrasturcture.mapper.UserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/18 2:55 PM
 */
@Service
public class UserInfoGatewayImpl implements UserInfoGateway {

    @Resource
    private UserInfoMapper userInfoMapper;


    @Override
    public UserInfoDTO getByUsername(String username) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        return OrikaMapperUtils.map(userInfo, UserInfoDTO.class);
    }

    @Override
    public void reg(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = OrikaMapperUtils.map(userInfoDTO, UserInfo.class);
        userInfoMapper.insert(userInfo);
        userInfoDTO.setId(userInfo.getId());
    }

    @Override
    public void update(UserInfoDTO userInfoDTO) {
        userInfoMapper.updateById(OrikaMapperUtils.map(userInfoDTO, UserInfo.class));
    }

    @Override
    public List<UserInfoDTO> queryByIds(List<Integer> userIds) {
        List<UserInfo> userInfos = userInfoMapper.selectBatchIds(userIds);
        return OrikaMapperUtils.mapList(userInfos, UserInfo.class, UserInfoDTO.class);
    }

    @Override
    public UserInfoDTO getByUserId(Integer userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        return OrikaMapperUtils.map(userInfo, UserInfoDTO.class);
    }

    @Override
    public PageInfo<UserInfoDTO> pageList(UserInfoDTO userInfoDTO) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Objects.nonNull(userInfoDTO.getStatus()), "status", userInfoDTO.getStatus())
                .like(StringUtils.isNotBlank(userInfoDTO.getDispName()), "disp_name", userInfoDTO.getDispName())
                .orderByDesc("gmt_create");
        Page<UserInfo> page = new Page<>(userInfoDTO.getPageNo(), userInfoDTO.getPageSize());
        Page<UserInfo> userInfoPage = userInfoMapper.selectPage(page, queryWrapper);
        List<UserInfo> list = userInfoPage.getRecords();
        List<UserInfoDTO> result = OrikaMapperUtils.mapList(list, UserInfo.class, UserInfoDTO.class);
        PageInfo<UserInfoDTO> pageInfo = new PageInfo<>();
        pageInfo.setList(result);
        pageInfo.setTotal(userInfoPage.getTotal());
        return pageInfo;
    }

    @Override
    public void limitLogin(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userInfoDTO.getId());
        userInfo.setStatus(userInfoDTO.getStatus());
        userInfoMapper.updateById(userInfo);
    }
}
