package com.pharos.app.service.collection;

import com.pharos.app.service.collection.req.CollectionActionReq;
import com.pharos.app.service.collection.vo.MyCollectionVO;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.collection.CollectionInfoGateway;
import com.pharos.domain.collection.dto.CollectionInfoDTO;
import com.pharos.domain.guidance.GuidanceGateway;
import com.pharos.domain.guidance.dto.GuidanceDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/8/3 10:41 AM
 */
@Service
public class CollectionService {

    @Resource
    private CollectionInfoGateway collectionInfoGateway;

    @Resource
    private GuidanceGateway guidanceGateway;

    public void action(CollectionActionReq req, Integer loginUserId) {
        CollectionInfoDTO collectionInfoDTO = OrikaMapperUtils.map(req, CollectionInfoDTO.class);
        collectionInfoDTO.setUserId(loginUserId);
        CollectionInfoDTO record = collectionInfoGateway.queryByUrlIdAndUserId(collectionInfoDTO);
        if (Objects.isNull(record)) {
            collectionInfoGateway.insert(collectionInfoDTO);
        } else {
            record.setStatus(req.getStatus());
            collectionInfoGateway.update(record);
        }
    }

    public List<MyCollectionVO> queryMyCollection(Integer loginUserId) {
        List<CollectionInfoDTO> list = collectionInfoGateway.queryByUserId(loginUserId);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<Integer> urlIds = list.stream().map(CollectionInfoDTO::getUrlId).collect(Collectors.toList());
        List<GuidanceDTO> guidanceDTOS = guidanceGateway.queryByIds(urlIds);
        Map<Integer, GuidanceDTO> map = guidanceDTOS.stream().collect(Collectors.toMap(GuidanceDTO::getId, x -> x, (a, b) -> b));
        List<MyCollectionVO> result = new ArrayList<>();
        urlIds.forEach(x -> {
            MyCollectionVO vo = new MyCollectionVO();
            GuidanceDTO guidanceDTO = map.get(x);
            if (Objects.nonNull(guidanceDTO)) {
                vo.setUrl(guidanceDTO.getUrl());
                result.add(vo);
            }
        });
        return result;
    }
}
