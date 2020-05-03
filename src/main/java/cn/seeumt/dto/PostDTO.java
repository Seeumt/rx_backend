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
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;




/**
 * @author Seeumt
 * @date 2019/12/8 14:51
 */
@Data
public class PostDTO {
    @ApiModelProperty(value = "动态Id",example="10002")
    private String postId;
    @ApiModelProperty(value = "用户Id",example="1001")
    private String userId;
    @ApiModelProperty(value = "用户名",example="Tips_001")
    private String username;
    @ApiModelProperty(value = "昵称",example="Seeumt")
    private String nickname;
    @ApiModelProperty(value = "头像url",example="https://www.tips.com/pics/avator/1.jpg")
    private String faceIcon;
    @ApiModelProperty(value = "是否关注",example="true")
    private Boolean isFollow;
    @ApiModelProperty(value = "动态类型",example="原创1/转发0")
    private Boolean type;
    @ApiModelProperty(value = "动态内容",example="hello Tips")
    private String content;
    @ApiModelProperty(value = "图片数组",example="['https://www.tips.com/pics/post/1.jpg']")
    private String[] imgUrls;
    @ApiModelProperty(value = "用户视图模型",example="{}")
    private UserVO userVO;
    @ApiModelProperty(value = "标签对象数组",example="[{},{}]")
    private List<TagVO> tags;
    @ApiModelProperty(value = "发布时间",example="2020-02-20 20:20")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "点赞点踩模型",example="{}")
    private LoveVO love;
    @ApiModelProperty(value = "评论数",example="80000")
    private Integer commentCount;
    @ApiModelProperty(value = "评论列表",example="[{},{}]")
    private List<Comment> comments;
    @ApiModelProperty(value = "点赞列表",example="[{},{}]")
    private List<Thumber> thumbers;
}
