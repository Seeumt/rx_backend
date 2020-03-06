package cn.seeumt.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Seeumt
 * @since 2019-12-21
 */
@Data
public class ArticleVO implements Serializable {


    private static final long serialVersionUID = -467880577878210466L;

    @JsonProperty("mediaId")
    private String articleId;

    /**
     * //标题
     */
    private String title;

    /**
     * //封面图片
     */
    @JsonProperty("cover")
    private String coverPicture;


    /**
     * //用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String faceIcon;

    /**
     * 点赞数
     */
    private Integer thumbCount;


}
