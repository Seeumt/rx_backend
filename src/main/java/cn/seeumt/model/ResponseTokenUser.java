package cn.seeumt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/1 12:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTokenUser {
    private String token;
    private UserDetail userDetail;

}
