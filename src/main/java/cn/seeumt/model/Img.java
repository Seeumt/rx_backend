package cn.seeumt.model;

import cn.seeumt.config.AliyunOssConfig;
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

public class Img {
    @Autowired
    private AliyunOssConfig aliyunOssConfig;

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
    private String parentId;

    public String getOssId() {
        return ossId;
    }

    public void setOssId(String ossId) {
        this.ossId = ossId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
