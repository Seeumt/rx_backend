package cn.seeumt.model;

import cn.seeumt.config.AliyunOssConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-08
 */
@Data
public class Img {

    /**
     * 图片id
     */
    @JsonProperty("imgId")
    private String ossId;

    /**
     * 图片地址
     */
    @JsonProperty("imgUrl")
    private String url;


    /**
     * 父级id
     */
    @JsonIgnore
    private String parentId;

    @JsonIgnore
    private Integer type;



}
