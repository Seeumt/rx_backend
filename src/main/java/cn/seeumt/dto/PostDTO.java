package cn.seeumt.dto;

import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.Date;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
/**
 * @author Seeumt
 * @date 2019/12/8 14:51
 */
public class PostDTO {

    private String postId;

    private Boolean type;

    private String content;

    private String imgId;

    private String userId;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    private List<Comment> comments;

    private List<Thumber> thumbers;
}
