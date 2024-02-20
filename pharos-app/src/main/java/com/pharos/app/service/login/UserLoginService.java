package com.pharos.app.service.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pharos.app.service.login.req.UserLoginReq;
import com.pharos.app.service.login.vo.UserLoginVO;
import com.pharos.common.encryption.MD5Util;
import com.pharos.common.exception.BizException;
import com.pharos.common.http.HttpUtil;
import com.pharos.common.utils.RedisUtil;
import com.pharos.domain.user.UserInfoGateway;
import com.pharos.domain.user.dto.UserInfoDTO;
import com.pharos.domain.user.dto.UserLoginDTO;
import com.pharos.domain.user.enums.UserRedisKeysEnum;
import com.pharos.domain.user.enums.UserStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: 用户登录
 * @date 2023/7/10 2:39 PM
 */
@Service
public class UserLoginService {
    private static final String LDAP_LOGIN_URL = "http://cmdb-tx-api.webuyops.com/api/ldap_login";

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserInfoGateway userInfoGateway;

    public UserLoginVO login(UserLoginReq userLoginReq) {
        UserInfoDTO userInfoDTO = this.checkStatus(userLoginReq);
        UserLoginDTO userLoginDTO = this.doLogin(userLoginReq);
        this.userInfoHandle(userInfoDTO, userLoginDTO);
        String token = this.generateToken(userLoginDTO);
        return UserLoginVO.builder().username(userLoginDTO.getUsername()).chineseName(userLoginDTO.getDispName()).token(token).build();
    }

    private void userInfoHandle(UserInfoDTO userInfoDTO, UserLoginDTO userLoginDTO) {
        if (Objects.nonNull(userInfoDTO)) {
            userInfoDTO.setGmtLastLogin(new Date());
            userLoginDTO.setId(String.valueOf(userInfoDTO.getId()));
            userInfoGateway.update(userInfoDTO);
        } else {
            UserInfoDTO infoDTO = new UserInfoDTO();
            infoDTO.setUsername(userLoginDTO.getUsername());
            infoDTO.setDispName(userLoginDTO.getDispName());
            userInfoGateway.reg(infoDTO);
            userLoginDTO.setId(String.valueOf(infoDTO.getId()));
        }
    }

    private String generateToken(UserLoginDTO userLoginDTO) {
        String token = MD5Util.encode(JSON.toJSONString(userLoginDTO) + System.currentTimeMillis());
        redisUtil.set(String.format(UserRedisKeysEnum.USER_LOGIN_ID.getKey(), userLoginDTO.getId()), token, UserRedisKeysEnum.USER_LOGIN_ID.getTime());
        redisUtil.set(String.format(UserRedisKeysEnum.USER_LOGIN_TOKEN.getKey(), token), JSON.toJSONString(userLoginDTO), UserRedisKeysEnum.USER_LOGIN_TOKEN.getTime());
        return token;
    }

    private UserInfoDTO checkStatus(UserLoginReq userLoginReq) {
        UserInfoDTO userInfoDTO = userInfoGateway.getByUsername(userLoginReq.getUsername());
        if (Objects.isNull(userInfoDTO)) {
            throw new BizException("账号不存在！");
        }
        Integer status = userInfoDTO.getStatus();
        if (Objects.equals(status, UserStatusEnum.DEACTIVATE.getStatus())) {
            throw new BizException("您的账号被限制登录！");
        }
        return userInfoDTO;
    }

    private UserLoginDTO doLogin(UserLoginReq userLoginReq) {
        Map<String, String> map = new HashMap<>();
        map.put("username", userLoginReq.getUsername());
        map.put("password", userLoginReq.getPassword());
        String result = HttpUtil.doPost(LDAP_LOGIN_URL, JSON.toJSONString(map), HttpUtil.JSON);
        if (StringUtils.isBlank(result)) {
            throw new BizException("登录失败，请稍后再试");
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer code = jsonObject.getInteger("code");
        if (code != 200) {
            throw new BizException(jsonObject.getString("error_msg"));
        }
        UserLoginDTO userLoginDTO = JSON.toJavaObject(jsonObject.getJSONObject("user"), UserLoginDTO.class);
        if (Objects.isNull(userLoginDTO)) {
            throw new BizException("登录失败，请稍后再试");
        }
        return userLoginDTO;
    }


    public UserLoginDTO getUserInfoByToken(String token) {
        String userInfo = redisUtil.get(String.format(UserRedisKeysEnum.USER_LOGIN_TOKEN.getKey(), token));
        if (StringUtils.isBlank(userInfo)) {
            return null;
        }
        return JSON.toJavaObject(JSONObject.parseObject(userInfo), UserLoginDTO.class);
    }
}
