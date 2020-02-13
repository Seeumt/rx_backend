package cn.seeumt.enums;

import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.Getter;

/**
 * @author Seeumt
 * @date 2019/12/9 20:34
 */
@Getter
public enum TipsFlash implements CodeEnum {
    /**
     * 10001--新建文章失败
     */
    ARTICLE_INSERT_FAILED(10001, "新建文章失败"),
    ARTICLE_THUMB_FAILED(10002, "文章点赞失败"),
    ABNORMAL_THUMB(10003, "刚刚好像有外星人给你点赞了"),
    THUMB_FAILED(10004, "点赞系统报错了哟"),
    ADD_TO_CART_FAILED(70001,"添加到购物车失败"),
    ADD_TO_ORDER_MASTER_FAILED(60001,"添加到主订单失败"),
    ADD_TO_ORDER_DETAIL_FAILED(60002,"添加到子订单失败"),
    INVAILD_ARGUMENT(70002, "参数不合法"),
    NULL_ARGUMENT(70003, "参数为空"),
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

    TipsFlash(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
