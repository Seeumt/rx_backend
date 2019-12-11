package cn.seeumt.dataobject;

import lombok.Data;

import java.util.Date;
@Data
public class Article {
    private String articleId;

    private String title;

    private String mdContent;

    private String htmlContent;

    private String ownerId;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private Boolean deleted;

}
