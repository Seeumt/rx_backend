package cn.seeumt.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonPropertyOrder("true")
public class Comment {
    @JsonProperty("id")
    private String commentId;

    private Byte type;

    private String userId;
    @JsonProperty("sendMsg")
    private String content;
    @JsonProperty("userName")
    private String username;
    @JsonProperty("headImgSrc")
    private String faceIcon;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private Boolean expand = false;

    private String parentId;

    private String apiRootId;

    private String targetUserId;

    @JsonProperty("targetUserName")
    private String targetUsername;

    @JsonProperty("thumbers")
    private List<Thumber> thumbers;

    private List<Comment> children;

    private Integer childrenCount;
}
