package com.pharos.common.response;


public enum BaseCode implements Code {
    SUCCESS(10000, "业务处理成功", "业务处理成功"),
    SYSTEM_FAILED(10001, "网络走神了,请稍后重试", "网络走神了,请稍后重试"),
    PARAM_ERROR(10002, "参数错误", "请检查参数是否正确"),
    LOGIN_INVALID(10003, "登录失效", "登录失效,请重新登录"),
    NEED_LOGIN(10004, "登录失效", "请先登录"),
    NOT_AUTHORITY(10005, "暂无操作权限", "暂无操作权限"),
    DATA_NOT_EXIST(10011, "数据不存在", "数据不存在"),

    ;

    private final int code;
    private final String info;
    private final String fixTips;

    BaseCode(int code, String info, String fixTips) {
        this.code = code;
        this.info = info;
        this.fixTips = fixTips;
    }

    /**
     * 错误码
     * eg: 200xx gateway
     * 300xx user
     * 400xx order
     * 500xx core
     * 600xx operator
     * 700xx admin
     * <p>
     * universal:
     * 10000 ~ 19999
     * 10000 success
     * 10001 system error
     * 10002 timed out
     * 10003 params error
     * 10004 rpc timeout
     * 10005 rpc invoke error
     *
     * @return
     */
    @Override
    public int getCode() {
        return this.code;
    }

    /**
     * 错误码说明(内部日志，统计，查看使用)
     *
     * @return
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * 错误码描述，对外输出
     *
     * @return
     */
    @Override
    public String getFixTips() {
        return this.fixTips;
    }
}
