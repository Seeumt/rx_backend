package cn.seeumt.dataobject;

import java.util.Date;

public class Love {
    private String id;

    private Byte type;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private String fromId;

    private String loveId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId == null ? null : fromId.trim();
    }

    public String getLoveId() {
        return loveId;
    }

    public void setLoveId(String loveId) {
        this.loveId = loveId == null ? null : loveId.trim();
    }
}