package cn.seeumt.security.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 11:18
 */
@Getter
public class VaildCodeException extends AuthenticationException {

    private static final long serialVersionUID = 6744633915417541851L;

    public VaildCodeException(String msg) {
        super(msg);
    }
}
