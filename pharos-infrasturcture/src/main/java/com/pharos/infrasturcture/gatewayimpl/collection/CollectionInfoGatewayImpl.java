package com.pharos.infrasturcture.gatewayimpl.collection;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.collection.CollectionInfoGateway;
import com.pharos.domain.collection.dto.CollectionInfoDTO;
import com.pharos.infrasturcture.entity.CollectionInfo;
import com.pharos.infrasturcture.mapper.CollectionInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/8/3 10:38 AM
 */
@Service
public class CollectionInfoGatewayImpl implements CollectionInfoGateway {

    @Resource
    private CollectionInfoMapper collectionInfoMapper;

    @Override
    public CollectionInfoDTO queryByUrlIdAndUserId(CollectionInfoDTO collectionInfoDTO) {
        QueryWrapper<CollectionInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("url_id", collectionInfoDTO.getUrlId())
                .eq("user_id", collectionInfoDTO.getUserId());
        CollectionInfo collectionInfo = collectionInfoMapper.selectOne(queryWrapper);
        return OrikaMapperUtils.map(collectionInfo, CollectionInfoDTO.class);
    }

    @Override
    public void insert(CollectionInfoDTO collectionInfoDTO) {
        collectionInfoMapper.insert(OrikaMapperUtils.map(collectionInfoDTO, CollectionInfo.class));
    }

    @Override
    public void update(CollectionInfoDTO collectionInfoDTO) {
        collectionInfoMapper.updateById(OrikaMapperUtils.map(collectionInfoDTO, CollectionInfo.class));
    }

    @Override
    public List<CollectionInfoDTO> queryByUserId(Integer userId) {
        QueryWrapper<CollectionInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .eq("status", 1);
        List<CollectionInfo> collectionInfos = collectionInfoMapper.selectList(queryWrapper);
        return OrikaMapperUtils.mapList(collectionInfos, CollectionInfo.class, CollectionInfoDTO.class);
    }
}
