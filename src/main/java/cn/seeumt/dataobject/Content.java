package cn.seeumt.dataobject;

import lombok.Data;

import java.util.Date;
@Data
public class Content {
    private String id;

    private Byte type;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    private String loveId;

    private String commentId;

    private String contentId;

    private String content;

}
