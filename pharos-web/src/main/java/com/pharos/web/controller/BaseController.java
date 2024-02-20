package com.pharos.web.controller;

import com.pharos.app.service.login.AdminLoginService;
import com.pharos.app.service.login.UserLoginService;
import com.pharos.common.exception.BizException;
import com.pharos.common.response.BaseCode;
import com.pharos.domain.admin.dto.AdminInfoDTO;
import com.pharos.domain.user.dto.UserLoginDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/4 1:42 PM
 */
@Controller
public class BaseController {

    private static final String TOKEN = "token";

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private AdminLoginService adminLoginService;

    public Integer getLoginUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //业务逻辑
        String token = request.getHeader(TOKEN);
        if (StringUtils.isBlank(token)) {
            throw new BizException(BaseCode.NEED_LOGIN);
        }
        UserLoginDTO userInfoDTO = userLoginService.getUserInfoByToken(token);
        if (Objects.isNull(userInfoDTO)) {
            throw new BizException(BaseCode.LOGIN_INVALID);
        }
        return Integer.valueOf(userInfoDTO.getId());
    }

    public Integer getLoginUserIdCanNull() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //业务逻辑
        String token = request.getHeader(TOKEN);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        UserLoginDTO userInfoDTO = userLoginService.getUserInfoByToken(token);
        if (Objects.isNull(userInfoDTO)) {
            return null;
        }
        return Integer.valueOf(userInfoDTO.getId());
    }

    public Integer getLoginAdminId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //业务逻辑
        String token = request.getHeader(TOKEN);
        if (StringUtils.isBlank(token)) {
            throw new BizException(BaseCode.NEED_LOGIN);
        }
        AdminInfoDTO adminInfoDTO = adminLoginService.getAdminInfoByToken(token);
        if (Objects.isNull(adminInfoDTO)) {
            throw new BizException(BaseCode.LOGIN_INVALID);
        }
        return adminInfoDTO.getId();
    }


}
