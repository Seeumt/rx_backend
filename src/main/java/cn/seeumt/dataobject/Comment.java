package cn.seeumt.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author Seeumt
 */
@Data
public class Comment {
    @TableId
    private String commentId;

    private Byte type;

    private String userId;

    private String content;

    private String commentPic;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private String parentId;

    private String apiRootId;

}
