package com.pharos.app.manager;

import com.alibaba.fastjson.JSON;
import com.pharos.app.service.guidance.vo.GuidanceVO;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.common.utils.RedisUtil;
import com.pharos.domain.guidance.GuidanceGateway;
import com.pharos.domain.guidance.constant.GuidanceRedisKey;
import com.pharos.domain.guidance.dto.GuidanceDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2024/2/20 6:01 PM
 */
@Component
public class GuidanceCacheManager {

    @Resource
    private GuidanceGateway guidanceGateway;

    @Resource
    private RedisUtil redisUtil;

    public void buildGuidanceCache() {
        List<GuidanceDTO> all = guidanceGateway.all();
        if (CollectionUtils.isEmpty(all)) {
            return;
        }
        List<GuidanceVO> guidanceVOS = OrikaMapperUtils.mapList(all, GuidanceDTO.class, GuidanceVO.class);
        redisUtil.set(GuidanceRedisKey.ALL_LIST, JSON.toJSONString(guidanceVOS));
        Map<Integer, List<GuidanceVO>> listMap = guidanceVOS.stream().collect(Collectors.groupingBy(GuidanceVO::getType));
        for (Integer key : listMap.keySet()) {
            List<GuidanceVO> list = listMap.get(key);
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }
            redisUtil.set(String.format(GuidanceRedisKey.TYPE_LIST, key), JSON.toJSONString(list));
        }
    }

}
