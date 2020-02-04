package cn.seeumt.form;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/31 14:49
 */
@Data
public class LoginUser {
    private String username;
    private String telephone;
    private String password;
    private Long code;
}
