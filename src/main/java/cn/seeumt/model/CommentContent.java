package cn.seeumt.model;

import cn.seeumt.dataobject.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CommentContent {

    private String id;

    private String content;

    private Date createTime;

    private String commentId;

    private String contentId;

    private List<UserInfo> thumbers;

    private List<Commenter> commenters;

}
