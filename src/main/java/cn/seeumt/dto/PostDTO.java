package cn.seeumt.dto;

import cn.seeumt.dataobject.Tag;
import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import cn.seeumt.vo.LoveVO;
import cn.seeumt.vo.TagVO;
import cn.seeumt.vo.UserVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;




/**
 * @author Seeumt
 * @date 2019/12/8 14:51
 */
@Data
public class PostDTO {

    private String postId;
    private String userId;
    private String username;
    private String faceIcon;
    private Boolean isFollow;
    private Boolean type;
    private String content;
    private String[] imgUrls;
    private UserVO userVO;
    private List<TagVO> tags;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;
    private LoveVO love;
    private Integer commentCount;
    private List<Comment> comments;
    private List<Thumber> thumbers;
}
