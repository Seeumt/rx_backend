package cn.seeumt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**

 * createAt: 2018/9/17
 */
@Data
@AllArgsConstructor
public class ResponseUserToken {
    private String token;
    private UserDetail userDetail;
}
