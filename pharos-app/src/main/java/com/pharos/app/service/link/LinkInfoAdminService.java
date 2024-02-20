package com.pharos.app.service.link;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.pharos.app.service.guidance.vo.GuidanceVO;
import com.pharos.app.service.link.req.LinkAuditReq;
import com.pharos.app.service.link.req.LinkInfoReq;
import com.pharos.common.exception.BizException;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.common.utils.RedisUtil;
import com.pharos.domain.guidance.GuidanceGateway;
import com.pharos.domain.guidance.constant.GuidanceRedisKey;
import com.pharos.domain.guidance.dto.GuidanceDTO;
import com.pharos.domain.link.LinkInfoGateway;
import com.pharos.domain.link.dto.LinkInfoDTO;
import com.pharos.domain.link.enums.LinkPrivacyEnum;
import com.pharos.domain.link.enums.LinkStatusEnum;
import com.pharos.domain.user.UserInfoGateway;
import com.pharos.domain.user.dto.UserInfoDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/18 11:40 AM
 */
@Service
public class LinkInfoAdminService {

    @Resource
    private LinkInfoGateway linkInfoGateway;

    @Resource
    private GuidanceGateway guidanceGateway;

    @Resource
    private UserInfoGateway userInfoGateway;

    @Resource
    private RedisUtil redisUtil;

    public PageInfo<LinkInfoDTO> list(LinkInfoReq req) {
        LinkInfoDTO linkInfoDTO = OrikaMapperUtils.map(req, LinkInfoDTO.class);
        PageInfo<LinkInfoDTO> result = linkInfoGateway.pageList(linkInfoDTO);
        if (CollectionUtils.isEmpty(result.getList())) {
            return result;
        }
        List<Integer> userIds = result.getList().stream().map(LinkInfoDTO::getUserId).collect(Collectors.toList());
        List<UserInfoDTO> userInfoDTOList = userInfoGateway.queryByIds(userIds);
        Map<Integer, UserInfoDTO> userInfoMap = userInfoDTOList.stream().collect(Collectors.toMap(UserInfoDTO::getId, x -> x, (a, b) -> b));
        result.getList().forEach(x -> {
            UserInfoDTO userInfoDTO = userInfoMap.get(x.getUserId());
            x.setUserName(Objects.isNull(userInfoDTO) ? "" : userInfoDTO.getDispName());
        });
        return result;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void audit(LinkAuditReq req, Integer loginAdminId) {
        LinkInfoDTO linkInfoDTO = linkInfoGateway.getById(req.getId());
        if (Objects.isNull(linkInfoDTO)) {
            throw new BizException("数据不存在");
        }
        Integer status = req.getStatus();
        linkInfoDTO.setStatus(status);
        linkInfoDTO.setAdminId(loginAdminId);
        if (Objects.equals(status, LinkStatusEnum.REJECT.getStatus())) {
            //审核驳回
            linkInfoDTO.setFailReason(req.getFailReason());
        }
        linkInfoDTO.setGmtModify(new Date());
        linkInfoGateway.update(linkInfoDTO);
        Integer privacy = linkInfoDTO.getPrivacy();
        if (Objects.equals(status, LinkStatusEnum.PASS.getStatus()) &&
                Objects.equals(privacy, LinkPrivacyEnum.ORDINARY.getStatus())) {
            //审核通过,并且不是私密链接的复制到导航信息中
            this.copy(linkInfoDTO);
            this.buildCache();
        }
    }

    private void copy(LinkInfoDTO linkInfoDTO) {
        GuidanceDTO guidanceDTO = new GuidanceDTO();
        guidanceDTO.setTitle(linkInfoDTO.getTitle());
        guidanceDTO.setIcon(linkInfoDTO.getIcon());
        guidanceDTO.setUrl(linkInfoDTO.getUrl());
        guidanceDTO.setType(linkInfoDTO.getType());
        guidanceDTO.setRank(1);
        guidanceGateway.insert(guidanceDTO);
    }

    private void buildCache() {
        List<GuidanceDTO> all = guidanceGateway.all();
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
