package com.pharos.common.response;


public interface Code {

    int getCode();

    /**
     * 错误码说明(内部日志，统计，查看使用)
     * @return
     */
    String getInfo();

    /**
     * 错误码描述，对外输出
     * @return
     */
    String getFixTips();
}
