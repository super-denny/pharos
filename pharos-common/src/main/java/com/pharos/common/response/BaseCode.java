package com.pharos.common.response;


public enum BaseCode implements Code {
    SUCCESS(10000, "业务处理成功", "业务处理成功"),
    SYSTEM_FAILD(10001, "网络走神了,请稍后重试", "网络走神了,请稍后重试"),
    PARAM_ERROR(10002, "参数错误", "请检查参数是否正确"),
    LOGIN_INVALID(10003, "登录失效", "登录失效,请重新登录"),
    NEED_LOGIN(10004, "登录失效", "请先登录"),
    CODE_INVALID(10005, "验证码失效", "验证码失效"),
    CODE_ERROR(10006, "验证码错误", "验证码错误"),
    USER_NOT_EXIST(10007, "用户不存在", "用户不存在"),
    ACCOUNT_ERROR(10008, "账号错误", "账号错误"),
    LOGIN_ERROR(10009, "登录失败", "登录失败"),
    OPERATION_FAILED(10010, "操作失败", "操作失败"),
    DATA_NOT_EXIST(10011, "数据不存在", "数据不存在"),
    ADD_CART_FAILED(10012, "加购失败", "加购失败"),
    ORDER_SUBMIT_FAILED(10013, "订单提交失败", "订单提交失败"),
    INVENTORY_SHORTAGE(10014, "库存不足", "库存不足"),;

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
