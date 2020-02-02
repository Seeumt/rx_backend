package cn.seeumt.security.loginmodel;

import cn.seeumt.model.OtpCode;
import lombok.Data;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 19:21
 */
@Data
public class Otp {
    private String telephone;
    private Long vaildCode;
}
