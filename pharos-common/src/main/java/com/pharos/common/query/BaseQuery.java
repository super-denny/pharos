package com.pharos.common.query;

import lombok.Data;

/**
 * @author wcj
 * @ClassName BaseQuery
 * @Description 分页
 * @createTime 2020-12-07
 */
@Data
public class BaseQuery {

    private Integer pageNo = 1;

    private Integer pageSize = 20;
}
