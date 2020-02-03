package cn.seeumt.security.exception;

import cn.seeumt.security.enums.SecurityEnum;
import lombok.Getter;

/**
 * @description: 自定义异常
 * @author: maxiao1
 * @date: 2019/9/13 17:21
 */
@Getter
public class TokenIsExpiredException extends Exception {

    private Integer code;



    public TokenIsExpiredException(SecurityEnum securityEnum) {
        super(securityEnum.getMsg());
        this.code = securityEnum.getCode();
    }

    public TokenIsExpiredException(String message) {
        super(message);
    }

    public TokenIsExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenIsExpiredException(Throwable cause) {
        super(cause);
    }

    public TokenIsExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
