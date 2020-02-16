package cn.seeumt.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
@Data
public class Love {

    @TableId
    private String loveId;

    private Byte type;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private String userId;

    private String apiRootId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;

    private String loveType;
}
