package cn.seeumt.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/19 19:20
 */
@Data
public class CommentMO implements Serializable {

    private static final long serialVersionUID = -2903316850655341905L;

    @JsonProperty("cenId")
    private String commentId;

    private Byte type;

    private String userId;

    @JsonProperty("userName")
    private String username;

    @JsonProperty("headImgSrc")
    private String faceIcon;

    @JsonProperty("sendMsg")
    private String content;

    private Date createTime;

    private Boolean enabled;

    private Boolean expand = false;

    private String parentId;

    private String apiRootId;

    @JsonProperty("reviewNum")
    private Integer childrenCount;

    @JsonProperty("reviewLess")
    List<CommentVO> commentVOS;
}
