package cn.seeumt.dataobject;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRole implements Serializable {


    private static final long serialVersionUID = 901521812461058849L;

    @TableId
    private String userId;

    private Integer roleId;


}
