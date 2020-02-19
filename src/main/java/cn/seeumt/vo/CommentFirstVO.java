package cn.seeumt.vo;

import cn.seeumt.model.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/17 17:03
 */
@Data
public class CommentFirstVO implements Serializable {
    private static final long serialVersionUID = -8090542044732428414L;
    @JsonProperty("id")
    private String commentId;

    private String userId;

    @JsonProperty("userName")
    private String username;

    @JsonProperty("headImgSrc")
    private String faceIcon;

    @JsonProperty("sendMsg")
    private String content;

    @JsonProperty("sendTime")
    private Date createTime;

    private Boolean expand = false;

    private String parentId;

    @JsonProperty("cenId")
    private String apiRootId;

    @JsonProperty("reviewLess")
    private List<CommentVO> commentVOS1;

    @JsonProperty("reviewLessss")
    private List<Comment> commentVOS;

    @JsonProperty("reviewNum")
    private Integer commentCount;

    private List<CommentVO> children;

}
