package cn.seeumt.form;

import lombok.Data;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/4 9:50
 */
@Data
public class TelLogin {
    private String telephone;
    private Long code;
    private String password;
}
