package cn.seeumt.dto;

import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import cn.seeumt.vo.CityVO;
import cn.seeumt.vo.TagVO;
import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(name = "tags")
    private List<TagVO> tagVos;

    private String headPicture;

    private String coverPicture;

    @JSONField(name = "viaCities")
    private List<CityVO> viaCitiesVos;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private Boolean deleted;

    private List<Comment> comments;

    private List<Thumber> thumbers;

}
