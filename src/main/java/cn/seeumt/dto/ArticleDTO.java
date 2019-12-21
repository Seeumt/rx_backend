package cn.seeumt.dto;

import cn.seeumt.dataobject.City;
import cn.seeumt.dataobject.Tag;
import cn.seeumt.dataobject.UserInfo;
import cn.seeumt.model.Comment;
import cn.seeumt.model.Commenter;
import cn.seeumt.model.Thumber;
import cn.seeumt.vo.CityVO;
import cn.seeumt.vo.TagVO;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("tags")
    private List<TagVO> tagVOS;

    private String headPicture;

    private String coverPicture;

    @JsonProperty("viaCities")
    private List<CityVO> viaCitiesVOS;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private Boolean deleted;

    private List<Comment> comments;

    private List<Thumber> thumbers;

}
