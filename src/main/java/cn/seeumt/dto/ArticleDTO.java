package cn.seeumt.dto;

import cn.seeumt.dataobject.City;
import cn.seeumt.dataobject.Tag;
import cn.seeumt.dataobject.UserInfo;
import cn.seeumt.model.Comment;
import cn.seeumt.model.Commenter;
import cn.seeumt.model.Thumber;
import cn.seeumt.vo.CityVO;
import cn.seeumt.vo.TagVO;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 14:51
 */

public class ArticleDTO {

    private String articleId;

    private String title;

    private String htmlContent;

    private String userId;

    private String username;

    private String nickname;

    private String faceIcon;

//    @JsonProperty("tags")
    @JSONField(name = "tags")
    private List<TagVO> tagVOS;

    private String headPicture;

    private String coverPicture;

//    @JsonProperty("viaCities")
    @JSONField(name = "viaCities")
    private List<CityVO> viaCitiesVOS;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private Boolean deleted;

    private List<Comment> comments;

    private List<Thumber> thumbers;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFaceIcon() {
        return faceIcon;
    }

    public void setFaceIcon(String faceIcon) {
        this.faceIcon = faceIcon;
    }

    public List<TagVO> getTagVOS() {
        return tagVOS;
    }

    public void setTagVOS(List<TagVO> tagVOS) {
        this.tagVOS = tagVOS;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public List<CityVO> getViaCitiesVOS() {
        return viaCitiesVOS;
    }

    public void setViaCitiesVOS(List<CityVO> viaCitiesVOS) {
        this.viaCitiesVOS = viaCitiesVOS;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Thumber> getThumbers() {
        return thumbers;
    }

    public void setThumbers(List<Thumber> thumbers) {
        this.thumbers = thumbers;
    }
}
