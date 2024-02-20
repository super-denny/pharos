package com.pharos.app.service.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pharos.app.service.login.req.AdminLoginReq;
import com.pharos.app.service.login.vo.AdminLoginVO;
import com.pharos.common.encryption.MD5Util;
import com.pharos.common.exception.BizException;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.common.utils.RedisUtil;
import com.pharos.domain.admin.AdminInfoGateway;
import com.pharos.domain.admin.dto.AdminInfoDTO;
import com.pharos.domain.admin.enums.AdminRedisKeysEnum;
import com.pharos.domain.admin.enums.AdminStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/7 9:52 AM
 */
@Service
public class AdminLoginService {

    @Resource
    private AdminInfoGateway adminInfoGateway;

    @Resource
    private RedisUtil redisUtil;

    public AdminLoginVO login(AdminLoginReq adminLoginReq) {
        AdminInfoDTO adminInfoDTO = adminInfoGateway.getByAccount(adminLoginReq.getAccount());
        if (Objects.isNull(adminInfoDTO)) {
            throw new BizException("账户不存在");
        }
        Integer status = adminInfoDTO.getStatus();
        if (!Objects.equals(status, AdminStatusEnum.NORMAL.getStatus())) {
            throw new BizException("账户已停用");
        }
        String salt = adminInfoDTO.getSalt();
        Integer encryptTimes = adminInfoDTO.getEncryptTimes();
        String psw = adminLoginReq.getPassword();
        String password = MD5Util.pwdEncrypt(psw, salt, encryptTimes);
        if (!Objects.equals(password, adminInfoDTO.getPassword())) {
            throw new BizException("密码错误");
        }
        //生成token
        String token = MD5Util.encode(JSON.toJSONString(adminInfoDTO) + System.currentTimeMillis());
        //缓存token
        redisUtil.set(String.format(AdminRedisKeysEnum.USER_LOGIN_ID.getKey(), adminInfoDTO.getId()), token, AdminRedisKeysEnum.USER_LOGIN_ID.getTime());
        redisUtil.set(String.format(AdminRedisKeysEnum.USER_LOGIN_TOKEN.getKey(), token), JSON.toJSONString(adminInfoDTO), AdminRedisKeysEnum.USER_LOGIN_TOKEN.getTime());
        AdminLoginVO loginVO = OrikaMapperUtils.map(adminInfoDTO, AdminLoginVO.class);
        loginVO.setToken(token);
        return loginVO;
    }

    public AdminInfoDTO getAdminInfoByToken(String token) {
        String info = redisUtil.get(String.format(AdminRedisKeysEnum.USER_LOGIN_TOKEN.getKey(), token));
        if (StringUtils.isBlank(info)) {
            return null;
        }
        return JSON.toJavaObject(JSON.parseObject(info), AdminInfoDTO.class);
    }

    public void renewalLease(String token, Integer id) {
        redisUtil.expire(String.format(AdminRedisKeysEnum.USER_LOGIN_ID.getKey(), id), AdminRedisKeysEnum.USER_LOGIN_ID.getTime());
        redisUtil.expire(String.format(AdminRedisKeysEnum.USER_LOGIN_TOKEN.getKey(), token), AdminRedisKeysEnum.USER_LOGIN_TOKEN.getTime());
    }

    public void logout(Integer loginAdminId) {
        String key = String.format(AdminRedisKeysEnum.USER_LOGIN_ID.getKey(), loginAdminId);
        String token = redisUtil.get(key);
        String tokenKey = String.format(AdminRedisKeysEnum.USER_LOGIN_TOKEN.getKey(), token);
        redisUtil.remove(key);
        redisUtil.remove(tokenKey);
    }
}
