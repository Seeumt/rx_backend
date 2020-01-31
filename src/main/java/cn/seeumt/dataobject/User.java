package cn.seeumt.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
@Data
public class User{
    @TableId
    private String userId;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean locked;
}
