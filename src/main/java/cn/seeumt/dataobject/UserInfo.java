package cn.seeumt.dataobject;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 4978418084622768954L;
    private String id;

    private String username;

    private String nickname;

    private String faceIcon;

    private Integer telephone;

    private String email;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private Boolean stu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getFaceIcon() {
        return faceIcon;
    }

    public void setFaceIcon(String faceIcon) {
        this.faceIcon = faceIcon == null ? null : faceIcon.trim();
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public Boolean getStu() {
        return stu;
    }

    public void setStu(Boolean stu) {
        this.stu = stu;
    }
}
