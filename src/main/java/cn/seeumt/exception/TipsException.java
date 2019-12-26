package cn.seeumt.exception;

import cn.seeumt.enums.ResultEnum;
import cn.seeumt.enums.TipsFlash;
import lombok.Getter;

@Getter
public class TipsException extends RuntimeException {

    private Integer code;

    public TipsException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public TipsException(TipsFlash tipsFlash) {
        super(tipsFlash.getMsg());
        this.code = tipsFlash.getCode();
    }

    public TipsException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }


}
