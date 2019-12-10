package cn.seeumt.dataobject;

import lombok.Data;

import java.util.Date;
@Data
public class PostComment {

    private String id;

    private Byte type;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private String replyId;

    private String postId;

    private String userId;

}
