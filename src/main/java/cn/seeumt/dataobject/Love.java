package cn.seeumt.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
@Data
public class Love {

    @TableId
    private String loveId;

    private Byte type;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private String userId;

    private String apiRootId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;

    public String getLoveId() {
        return loveId;
    }

    public void setLoveId(String loveId) {
        this.loveId = loveId == null ? null : loveId.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getApiRootId() {
        return apiRootId;
    }

    public void setApiRootId(String apiRootId) {
        this.apiRootId = apiRootId == null ? null : apiRootId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
