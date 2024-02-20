package com.pharos.web.interceptor;


import com.pharos.app.service.login.AdminLoginService;
import com.pharos.common.annotation.NoLogin;
import com.pharos.common.exception.BizException;
import com.pharos.common.response.BaseCode;
import com.pharos.common.utils.RedisUtil;
import com.pharos.domain.admin.dto.AdminInfoDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author wcj
 * @ClassName LoginInterceptor
 * @Description 自定义登录拦截器
 * @createTime 2021-09-12
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    private static final String TOKEN = "token";


    private static AdminLoginInterceptor loginInterceptor;

    @PostConstruct
    public void init() {
        loginInterceptor = this;
    }

    @Resource
    private AdminLoginService adminLoginService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        NoLogin methodAnnotation = method.getAnnotation(NoLogin.class);
        // 有 @Login 注解，需要认证
        if (Objects.isNull(methodAnnotation)) {
            //业务逻辑
            String token = request.getHeader(TOKEN);
            if (StringUtils.isBlank(token)) {
                throw new BizException(BaseCode.NEED_LOGIN);
            }
            AdminInfoDTO adminInfoDTO = loginInterceptor.adminLoginService.getAdminInfoByToken(token);
            if (Objects.isNull(adminInfoDTO)) {
                throw new BizException(BaseCode.LOGIN_INVALID);
            }
            //登录续租
            loginInterceptor.adminLoginService.renewalLease(token, adminInfoDTO.getId());
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {

    }
}
