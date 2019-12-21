package cn.seeumt.dto;

import cn.seeumt.dataobject.City;
import cn.seeumt.dataobject.Tag;
import cn.seeumt.dataobject.UserInfo;
import cn.seeumt.model.Commenter;
import cn.seeumt.model.Thumber;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 14:51
 */
@Data
public class ArticleDTO {

    private String articleId;

    private String title;

    private String htmlContent;

    private String userId;

    private String username;

    private String nickname;

    private String faceIcon;

    private List<Tag> tags;

    private String headPicture;

    private String coverPicture;

    private List<City> viaCities;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private Boolean deleted;

    private List<Thumber> thumbers;

}
