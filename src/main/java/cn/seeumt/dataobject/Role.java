package cn.seeumt.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seeumt
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @TableId
    private Integer roleId;
    private String name;


}
