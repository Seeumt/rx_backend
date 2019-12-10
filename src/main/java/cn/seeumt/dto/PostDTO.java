package cn.seeumt.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostDTO {
    private String id;

    private Byte type;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private String replyId;

    private String postId;

    private String userId;

    List<PostDTO> children;
}
