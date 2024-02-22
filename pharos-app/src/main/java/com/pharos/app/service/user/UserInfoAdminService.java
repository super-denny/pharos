package com.pharos.app.service.user;

import com.github.pagehelper.PageInfo;
import com.pharos.app.service.user.req.UserInfoAdminReq;
import com.pharos.app.service.user.req.UserInfoAdminUpdateReq;
import com.pharos.app.service.user.vo.UserInfoAdminListVO;
import com.pharos.common.encryption.MD5Util;
import com.pharos.common.encryption.SaltUtil;
import com.pharos.common.enums.DeleteTagEnum;
import com.pharos.common.exception.BizException;
import com.pharos.common.response.BaseCode;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.user.UserInfoGateway;
import com.pharos.domain.user.dto.UserInfoDTO;
import org.apache.commons.lang3.StringUtils;
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

    private static final String DEFAULT_PSW = "user123456";


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

    public void update(UserInfoAdminUpdateReq req) {
        Integer id = req.getId();
        if (Objects.isNull(id)) {
            this.reg(req);
        } else {
            this.modify(req);
        }
    }

    private void modify(UserInfoAdminUpdateReq req) {
        UserInfoDTO infoDTO = userInfoGateway.getByUserId(req.getId());
        if (Objects.isNull(infoDTO)) {
            throw new BizException(BaseCode.DATA_NOT_EXIST);
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(req.getId());
        if (StringUtils.isNotBlank(req.getNickname())) {
            userInfoDTO.setNickname(req.getNickname());
        }
        if (StringUtils.isNotBlank(req.getPassword())) {
            String salt = SaltUtil.generateSalt();
            int encryptTimes = SaltUtil.getEncryptTimes();
            userInfoDTO.setPassword(MD5Util.pwdEncrypt(req.getPassword(), salt, encryptTimes));
            userInfoDTO.setSalt(salt);
            userInfoDTO.setEncryptTimes(encryptTimes);
        }
        userInfoGateway.update(userInfoDTO);
    }

    private void setPassword(UserInfoDTO infoDTO) {
        String salt = SaltUtil.generateSalt();
        int encryptTimes = SaltUtil.getEncryptTimes();
        infoDTO.setPassword(MD5Util.pwdEncrypt(DEFAULT_PSW, salt, encryptTimes));
        infoDTO.setSalt(salt);
        infoDTO.setEncryptTimes(encryptTimes);
    }

    private void reg(UserInfoAdminUpdateReq req) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setAccount(req.getAccount());
        userInfoDTO.setNickname(req.getNickname());
        this.setPassword(userInfoDTO);
        userInfoGateway.reg(userInfoDTO);
    }

    public void delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new BizException(BaseCode.PARAM_ERROR);
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(id);
        userInfoDTO.setDeleteTag(DeleteTagEnum.DELETED.getStatus());
        userInfoGateway.update(userInfoDTO);
    }

    public UserInfoAdminListVO detail(Integer id) {
        UserInfoDTO infoDTO = userInfoGateway.getByUserId(id);
        return OrikaMapperUtils.map(infoDTO, UserInfoAdminListVO.class);
    }

    public void updatePwd(UserInfoAdminUpdateReq req) {
        if (Objects.isNull(req.getId())) {
            throw new BizException(BaseCode.PARAM_ERROR);
        }
        this.modify(req);
    }
}
