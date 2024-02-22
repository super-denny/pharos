package com.pharos.app.service.guidance;

import com.github.pagehelper.PageInfo;
import com.pharos.app.service.guidance.req.GuidanceTypeAdminReq;
import com.pharos.app.service.guidance.req.GuidanceTypeAdminUpdateReq;
import com.pharos.app.service.guidance.vo.GuidanceTypeVO;
import com.pharos.common.enums.DeleteTagEnum;
import com.pharos.common.exception.BizException;
import com.pharos.common.response.BaseCode;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.common.utils.RedisUtil;
import com.pharos.domain.guidance.GuidanceGateway;
import com.pharos.domain.guidance.GuidanceTypeGateway;
import com.pharos.domain.guidance.constant.GuidanceTypeRedisKey;
import com.pharos.domain.guidance.dto.GuidanceTypeDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/3 5:55 PM
 */
@Service
public class GuidanceTypeAdminService {

    @Resource
    private GuidanceTypeGateway guidanceTypeGateway;

    @Resource
    private GuidanceGateway guidanceGateway;

    @Resource
    private RedisUtil redisUtil;

    public PageInfo<GuidanceTypeDTO> list(GuidanceTypeAdminReq guidanceTypeAdminReq) {
        GuidanceTypeDTO guidanceTypeDTO = OrikaMapperUtils.map(guidanceTypeAdminReq, GuidanceTypeDTO.class);
        return guidanceTypeGateway.pageList(guidanceTypeDTO);
    }

    public void update(GuidanceTypeAdminUpdateReq guidanceTypeAdminUpdateReq) {
        Integer id = guidanceTypeAdminUpdateReq.getId();
        GuidanceTypeDTO guidanceTypeDTO = OrikaMapperUtils.map(guidanceTypeAdminUpdateReq, GuidanceTypeDTO.class);
        if (Objects.nonNull(id)) {
            guidanceTypeGateway.update(guidanceTypeDTO);
        } else {
            guidanceTypeGateway.insert(guidanceTypeDTO);
        }
        redisUtil.remove(GuidanceTypeRedisKey.ALL_LIST);
    }

    public void delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new BizException(BaseCode.PARAM_ERROR);
        }
        Long count = guidanceGateway.countByTypeId(id);
        if (Objects.nonNull(count) && count > 0) {
            throw new BizException("该类目下存在有效数据，禁止删除");
        }
        GuidanceTypeDTO guidanceTypeDTO = new GuidanceTypeDTO();
        guidanceTypeDTO.setId(id);
        guidanceTypeDTO.setDeleteTag(DeleteTagEnum.DELETED.getStatus());
        guidanceTypeGateway.update(guidanceTypeDTO);
        redisUtil.remove(GuidanceTypeRedisKey.ALL_LIST);
    }

    public GuidanceTypeDTO detail(Integer id) {
        if (Objects.isNull(id)) {
            throw new BizException(BaseCode.PARAM_ERROR);
        }
        return guidanceTypeGateway.detail(id);
    }

    public List<GuidanceTypeVO> all() {
        List<GuidanceTypeDTO> all = guidanceTypeGateway.all();
        return OrikaMapperUtils.mapList(all, GuidanceTypeDTO.class, GuidanceTypeVO.class);
    }
}
