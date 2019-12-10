package cn.seeumt.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
//@JsonPropertyOrder("true")
public class PostComment {
    @JsonProperty("commentId")
    private String id;

    private Byte type;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private String replyId;

    private String postId;

    private String userId;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<PostComment> children;
}
