package com.pharos.domain.collection;

import com.pharos.domain.collection.dto.CollectionInfoDTO;

import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/8/3 10:38 AM
 */
public interface CollectionInfoGateway {

    CollectionInfoDTO queryByUrlIdAndUserId(CollectionInfoDTO collectionInfoDTO);

    void insert(CollectionInfoDTO collectionInfoDTO);

    void update(CollectionInfoDTO collectionInfoDTO);

    List<CollectionInfoDTO> queryByUserId(Integer userId);
}
