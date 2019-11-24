package cn.seeumt.exception;

import cn.seeumt.enums.ResultEnum;
import lombok.Getter;

@Getter
public class TipsException extends RuntimeException {
    private Integer code;

    public TipsException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public TipsException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
