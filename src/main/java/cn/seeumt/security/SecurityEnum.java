package cn.seeumt.security;

import cn.seeumt.enums.CodeEnum;
import lombok.Getter;

/**
 * @author Seeumt
 * @date 2020/2/1
 */
@Getter
public enum SecurityEnum implements CodeEnum {
    /**
     * SpringSecurity
     */
    TOKEN_EXPIRED(30001, "Token已过期！"),
    NOT_LOGIN(30002, "未登录，请登录！"),
    LACK_OF_AUTHORITY(30003, "权限不足！"),
    ABNORMAL_THUMB(10003, "刚刚好像有外星人给你点赞了"),
    THUMB_FAILED(10004, "点赞系统报错了哟"),
    ADD_TO_CART_FAILED(70001,"添加到购物车失败"),
    ADD_TO_ORDER_MASTER_FAILED(60001,"添加到主订单失败"),
    ADD_TO_ORDER_DETAIL_FAILED(60002,"添加到子订单失败"),
    INVAILD_ARGUMENT(70002, "参数不合法"),
    CLEAN_CART_FAILED(70004, "清空购物车失败"),
    CATEGORY_QUERY_FAILED(80001, "目录查询失败"),



    ;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;

    SecurityEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
