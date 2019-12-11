package cn.seeumt.dto;

import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.Date;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostDTO {

    private String postId;

    private Boolean type;

    private String content;

    private String imgId;

    private String userId;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    List<Comment> comments;

    List<Thumber> thumbers;
}
