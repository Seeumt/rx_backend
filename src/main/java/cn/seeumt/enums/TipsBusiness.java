package cn.seeumt.enums;

import lombok.Getter;

/**
 * @author Seeumt
 * @date 2019/12/8 14:19
 */
@Getter
public enum TipsBusiness implements CodeEnum {
    /**
     *
     */
    CANCELED(0,"已取消"),
    NO_PAY(10,"未支付"),
    PAID(20,"已付款"),
    SHIPPED(40,"已发货"),
    ORDER_FINISH(50,"订单完成"),
    ORDER_CLOSE(60,"订单关闭"),
    ONLINE_PAY(1,"在线支付"),

    INVAILD_ARGUMENT(70002, "参数不合法"),
    EMPTY_CART(70003, "购物车为空"),

    SOUVENIR_NOT_EXIST(10004, "该纪念品不存在"),
    NOT_ON_SALE(10001,"不在出售状态"),
    ON_SALE(1,"在出售状态"),
    LOW_STOCKS(10003, "产品库存不足"),

    ;

    /**
     * 标识码
     */
    private Integer code;
    /**
     * 信息
     */
    private String msg;

    TipsBusiness(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
