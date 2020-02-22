package cn.seeumt.enums;
import lombok.Getter;

/**
 * @author Seeumt
 */
@Getter
public enum ResultEnum implements CodeEnum {
    /**
     *
     */
    SUCCESS(10000, "成功"),
    ;
    /**
     * 响应码
     */
    public Integer code;
    /**
     * 响应解释
     */
    public String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
