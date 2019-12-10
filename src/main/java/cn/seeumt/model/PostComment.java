package cn.seeumt.model;

import cn.seeumt.dataobject.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
//@JsonPropertyOrder("true")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class PostComment {

    private String id;

    private Byte type;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private String replyId;

    private String postId;

    private String userId;

    @JsonProperty("thumbers")
    private List<Thumber> thumbers;


    private List<PostComment> children;
}
