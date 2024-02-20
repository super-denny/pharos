package com.pharos.app.service.user;

import com.github.pagehelper.PageInfo;
import com.pharos.app.service.user.req.UserInfoAdminReq;
import com.pharos.common.exception.BizException;
import com.pharos.common.response.BaseCode;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.link.dto.LinkInfoDTO;
import com.pharos.domain.user.UserInfoGateway;
import com.pharos.domain.user.dto.UserInfoDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/11/24 2:24 PM
 */
@Service
public class UserInfoAdminService {

    @Resource
    private UserInfoGateway userInfoGateway;


    public PageInfo<UserInfoDTO> list(UserInfoAdminReq userInfoAdminReq) {
        return userInfoGateway.pageList(OrikaMapperUtils.map(userInfoAdminReq, UserInfoDTO.class));
    }

    public void limitLogin(UserInfoAdminReq userInfoAdminReq) {
        Integer id = userInfoAdminReq.getId();
        if (Objects.isNull(id)) {
            throw new BizException(BaseCode.PARAM_ERROR);
        }
        userInfoGateway.limitLogin(OrikaMapperUtils.map(userInfoAdminReq, UserInfoDTO.class));
    }
}
