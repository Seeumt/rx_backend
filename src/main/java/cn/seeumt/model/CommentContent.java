package cn.seeumt.model;

import cn.seeumt.dataobject.UserInfo;
import lombok.Data;

import java.util.Date;
@Data
public class CommentContent {

    private String id;

    private String content;

    private Date createTime;

    private String commentId;

}
