package cn.seeumt.model;

import cn.seeumt.dataobject.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CommentContent {// TODO: 2019/12/9 Oops~~~

    private String id;

    private String content;

    private Date createTime;

    private String commentId;

    private String contentId;

    private String fromId;

    private String loveId;

    private List<UserInfo> thumbers;

    private List<Commenter> commenters;

}
