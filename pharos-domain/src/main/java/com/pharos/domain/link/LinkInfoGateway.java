package com.pharos.domain.link;

import com.github.pagehelper.PageInfo;
import com.pharos.domain.link.dto.LinkInfoDTO;

import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/19 7:14 PM
 */
public interface LinkInfoGateway {
    void insert(LinkInfoDTO linkInfoDTO);

    PageInfo<LinkInfoDTO> pageList(LinkInfoDTO linkInfoDTO);

    void update(LinkInfoDTO linkInfoDTO);

    List<LinkInfoDTO> getSubmitList(Integer userId);

    LinkInfoDTO getById(Integer id);

    List<LinkInfoDTO> queryPrivacyList(Integer userId);
}
