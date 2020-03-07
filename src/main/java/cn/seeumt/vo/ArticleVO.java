package cn.seeumt.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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


    private static final long serialVersionUID = 8980049727757275555L;
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
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String faceIcon;

    /**
     * //html内容
     */
    private String htmlContent;

    /**
     * 点赞数
     */
    private Integer thumbCount;

    /**
     * //创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    /**
     * //更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;


}
