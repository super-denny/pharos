package com.pharos.app.service.guidance;

import com.alibaba.fastjson.JSONArray;
import com.pharos.app.manager.GuidanceCacheManager;
import com.pharos.app.service.guidance.vo.GuidanceVO;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.common.utils.RedisUtil;
import com.pharos.domain.collection.CollectionInfoGateway;
import com.pharos.domain.collection.dto.CollectionInfoDTO;
import com.pharos.domain.guidance.GuidanceGateway;
import com.pharos.domain.guidance.constant.GuidanceRedisKey;
import com.pharos.domain.link.LinkInfoGateway;
import com.pharos.domain.link.dto.LinkInfoDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
 * @date 2023/7/3 5:55 PM
 */
@Service
public class GuidanceService {
    @Resource
    private CollectionInfoGateway collectionInfoGateway;

    @Resource
    private LinkInfoGateway linkInfoGateway;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private GuidanceCacheManager guidanceCacheManager;

    @PostConstruct
    private void loadData() {
        guidanceCacheManager.buildGuidanceCache();
    }


    public List<GuidanceVO> list(Integer type, Integer userId) {
        String value = "";
        if (Objects.isNull(type) || Objects.equals(type, NumberUtils.INTEGER_ZERO)) {
            value = redisUtil.get(GuidanceRedisKey.ALL_LIST);
        } else {
            value = redisUtil.get(String.format(GuidanceRedisKey.TYPE_LIST, type));
        }
        if (StringUtils.isBlank(value)) {
            return new ArrayList<>();
        }
        List<GuidanceVO> guidanceVOS = JSONArray.parseArray(value, GuidanceVO.class);
        List<CollectionInfoDTO> collectionList = null;
        if (Objects.nonNull(userId)) {
            collectionList = collectionInfoGateway.queryByUserId(userId);
        }
        if (CollectionUtils.isNotEmpty(collectionList)) {
            Map<Integer, CollectionInfoDTO> map = collectionList.stream().collect(Collectors.toMap(CollectionInfoDTO::getUrlId, x -> x, (a, b) -> b));
            guidanceVOS.forEach(x -> {
                CollectionInfoDTO dto = map.get(x.getId());
                if (Objects.nonNull(dto)) {
                    x.setCollectionStatus(dto.getStatus());
                }
            });
        }
        return guidanceVOS;
    }

    public List<GuidanceVO> privacyList(Integer userId) {
        List<LinkInfoDTO> list = linkInfoGateway.queryPrivacyList(userId);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return OrikaMapperUtils.mapList(list, LinkInfoDTO.class, GuidanceVO.class);
    }
}
