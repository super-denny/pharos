package com.pharos.app.service.guidance;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pharos.app.service.guidance.vo.GuidanceTypeVO;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.common.utils.RedisUtil;
import com.pharos.domain.guidance.GuidanceTypeGateway;
import com.pharos.domain.guidance.constant.GuidanceTypeRedisKey;
import com.pharos.domain.guidance.dto.GuidanceTypeDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 5:55 PM
 */
@Service
public class GuidanceTypeService {

    @Resource
    private GuidanceTypeGateway guidanceTypeGateway;

    @Resource
    private RedisUtil redisUtil;

    @PostConstruct
    private void loadData() {
        List<GuidanceTypeDTO> all = guidanceTypeGateway.all();
        List<GuidanceTypeVO> guidanceTypeVOS = OrikaMapperUtils.mapList(all, GuidanceTypeDTO.class, GuidanceTypeVO.class);
        redisUtil.set(GuidanceTypeRedisKey.ALL_LIST, JSON.toJSONString(guidanceTypeVOS));
    }


    public List<GuidanceTypeVO> all() {
        String listStr = redisUtil.get(GuidanceTypeRedisKey.ALL_LIST);
        if (StringUtils.isNotBlank(listStr)) {
            return JSONArray.parseArray(listStr, GuidanceTypeVO.class);
        }
        List<GuidanceTypeDTO> all = guidanceTypeGateway.all();
        List<GuidanceTypeVO> guidanceTypeVOS = OrikaMapperUtils.mapList(all, GuidanceTypeDTO.class, GuidanceTypeVO.class);
        redisUtil.set(GuidanceTypeRedisKey.ALL_LIST, JSON.toJSONString(guidanceTypeVOS));
        return guidanceTypeVOS;
    }
}
