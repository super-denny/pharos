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
        infoDTO.setNickname(req.getNickname());
        if (!Objects.equals(MD5Util.pwdEncrypt(req.getPassword(), infoDTO.getSalt(), infoDTO.getEncryptTimes()), infoDTO.getPassword())) {
            this.setPassword(req, infoDTO);
        }
        userInfoGateway.update(infoDTO);
    }

    private void setPassword(UserInfoAdminUpdateReq req, UserInfoDTO infoDTO) {
        String salt = SaltUtil.generateSalt();
        int encryptTimes = SaltUtil.getEncryptTimes();
        infoDTO.setPassword(MD5Util.pwdEncrypt(req.getPassword(), salt, encryptTimes));
        infoDTO.setSalt(salt);
        infoDTO.setEncryptTimes(encryptTimes);
    }

    private void reg(UserInfoAdminUpdateReq req) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setAccount(req.getAccount());
        userInfoDTO.setNickname(req.getNickname());
        this.setPassword(req, userInfoDTO);
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
}
