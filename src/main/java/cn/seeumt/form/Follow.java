package cn.seeumt.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/11 15:34
 */
@Data
public class Follow {
    @NotNull
    private String userId;
    @NotNull
    private String idolId;
}
