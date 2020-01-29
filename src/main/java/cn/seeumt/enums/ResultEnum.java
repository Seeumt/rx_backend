package cn.seeumt.enums;

import lombok.Getter;

@Getter
public enum ResultEnum implements CodeEnum {
    SUCCESS(10000, "成功"),
    ;

    public Integer code;
    public String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
