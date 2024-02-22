package com.pharos.domain.user;

import com.github.pagehelper.PageInfo;
import com.pharos.domain.user.dto.UserInfoDTO;

import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/10 2:55 PM
 */
public interface UserInfoGateway {

    UserInfoDTO getByAccount(String account);

    void reg(UserInfoDTO userInfoDTO);

    void update(UserInfoDTO userInfoDTO);

    List<UserInfoDTO> queryByIds(List<Integer> userIds);

    UserInfoDTO getByUserId(Integer userId);

    PageInfo<UserInfoDTO> pageList(UserInfoDTO userInfoDTO);

    void limitLogin(UserInfoDTO userInfoDTO);
}
