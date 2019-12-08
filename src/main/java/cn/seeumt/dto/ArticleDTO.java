package cn.seeumt.dto;

import cn.seeumt.dataobject.UserInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 14:51
 */
@Data
public class ArticleDTO {
    private String id;

    private String title;

    private String mdContent;

    private String htmlContent;

    private String loveId;

    private String commentId;

    private String userId;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private Boolean deleted;

    private List<UserInfo> thumbers;
}
