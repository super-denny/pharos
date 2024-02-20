package com.pharos.domain.admin;

import com.github.pagehelper.PageInfo;
import com.pharos.domain.admin.dto.AdminInfoDTO;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/4 11:05 AM
 */
public interface AdminInfoGateway {
    AdminInfoDTO getByAccount(String account);

    PageInfo<AdminInfoDTO> list(AdminInfoDTO adminInfoDTO);

    void add(AdminInfoDTO adminInfoDTO);

    void update(AdminInfoDTO adminInfoDTO);

    AdminInfoDTO detail(Integer id);
}
