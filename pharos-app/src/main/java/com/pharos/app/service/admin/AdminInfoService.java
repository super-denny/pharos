package com.pharos.app.service.admin;

import com.github.pagehelper.PageInfo;
import com.pharos.app.service.admin.req.AdminListReq;
import com.pharos.app.service.admin.req.AdminUpdateReq;
import com.pharos.app.service.admin.vo.AdminDetailVO;
import com.pharos.common.encryption.MD5Util;
import com.pharos.common.encryption.SaltUtil;
import com.pharos.common.enums.DeleteTagEnum;
import com.pharos.common.exception.BizException;
import com.pharos.common.response.BaseCode;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.admin.AdminInfoGateway;
import com.pharos.domain.admin.dto.AdminInfoDTO;
import com.pharos.domain.admin.enums.AdminTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/4 10:51 AM
 */
@Service
public class AdminInfoService {

    @Resource
    private AdminInfoGateway adminInfoGateway;

    private static final String DEFAULT_AVATAR = "https://cdn.webuy.ai/message/assets/img/2023/07/07/c9ad21cc-3e55-47f4-962d-c9f0434841b9__16KB____size300x254.jpeg";

    private static final String DEFAULT_PWD = "a123456";

    public PageInfo<AdminInfoDTO> list(AdminListReq adminListReq) {
        AdminInfoDTO adminInfoDTO = OrikaMapperUtils.map(adminListReq, AdminInfoDTO.class);
        return adminInfoGateway.list(adminInfoDTO);
    }

    public void add(AdminUpdateReq adminUpdateReq) {
        AdminInfoDTO infoDTO = adminInfoGateway.getByAccount(adminUpdateReq.getAccount());
        if (Objects.nonNull(infoDTO)) {
            throw new BizException("账号已存在");
        }
        AdminInfoDTO adminInfoDTO = OrikaMapperUtils.map(adminUpdateReq, AdminInfoDTO.class);
        if (StringUtils.isBlank(adminInfoDTO.getAvatar())) {
            adminInfoDTO.setAvatar(DEFAULT_AVATAR);
        }
        String salt = SaltUtil.generateSalt();
        int encryptTimes = SaltUtil.getEncryptTimes();
        String password = MD5Util.pwdEncrypt(DEFAULT_PWD, salt, encryptTimes);
        adminInfoDTO.setPassword(password);
        adminInfoDTO.setSalt(salt);
        adminInfoDTO.setEncryptTimes(encryptTimes);
        adminInfoGateway.add(adminInfoDTO);
    }

    public void update(AdminUpdateReq adminUpdateReq) {
        Integer id = adminUpdateReq.getId();
        if (Objects.isNull(id)) {
            this.add(adminUpdateReq);
        } else {
            AdminInfoDTO adminInfoDTO = OrikaMapperUtils.map(adminUpdateReq, AdminInfoDTO.class);
            adminInfoGateway.update(adminInfoDTO);
        }
    }

    public AdminDetailVO detail(Integer id) {
        AdminInfoDTO adminInfoDTO = adminInfoGateway.detail(id);
        return OrikaMapperUtils.map(adminInfoDTO, AdminDetailVO.class);
    }

    public void delete(Integer id) {
        AdminInfoDTO adminInfoDTO = new AdminInfoDTO();
        adminInfoDTO.setId(id);
        adminInfoDTO.setDeleteTag(DeleteTagEnum.DELETED.getStatus());
        adminInfoGateway.update(adminInfoDTO);
    }

    public void updatePwd(AdminUpdateReq req, Integer adminId) {
        if (Objects.isNull(req.getId())) {
            throw new BizException(BaseCode.PARAM_ERROR);
        }
        String password = req.getPassword();
        if (StringUtils.isBlank(password)) {
            throw new BizException(BaseCode.PARAM_ERROR);
        }
        AdminInfoDTO detail = adminInfoGateway.detail(adminId);
        if (Objects.isNull(detail)) {
            throw new BizException(BaseCode.PARAM_ERROR);
        }
        Integer type = detail.getType();
        if (Objects.equals(type, AdminTypeEnum.NORMAL.getType())) {
            throw new BizException(BaseCode.NOT_AUTHORITY);
        }
        AdminInfoDTO adminInfoDTO = new AdminInfoDTO();
        adminInfoDTO.setId(req.getId());
        String salt = SaltUtil.generateSalt();
        int encryptTimes = SaltUtil.getEncryptTimes();
        adminInfoDTO.setSalt(salt);
        adminInfoDTO.setEncryptTimes(encryptTimes);
        adminInfoDTO.setPassword(MD5Util.pwdEncrypt(password, salt, encryptTimes));
        adminInfoGateway.update(adminInfoDTO);
    }
}
