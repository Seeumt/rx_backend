package cn.seeumt.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/17 17:03
 */
@Data
public class CommentVO implements Serializable {
    private static final long serialVersionUID = -8090542044732428414L;
    @JsonProperty("cenId")
    private String commentId;

    private Byte type;

    private String userId;

    private String username;

    @JsonProperty("userName")
    private String nickname;

    @JsonProperty("headImgSrc")
    private String faceIcon;

    private String targetUserId;

    private String targetUsername;

    @JsonProperty("targetUserName")
    private String targetNickname;

    @JsonProperty("sendMsg")
    private String content;

    private String commentPic;

    @JsonProperty("sendTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    private Boolean enabled;

    private Boolean expand;

    private String parentId;

    private String apiRootId;

    private Integer childrenCount;

}
