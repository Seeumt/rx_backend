package cn.seeumt.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
//@JsonPropertyOrder("true")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Comment {
    private String commentId;

    private Byte type;

    private String userId;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Boolean enabled;

    private String parentId;

    private String apiRootId;

    @JsonProperty("thumbers")
    private List<Thumber> thumbers;


    private List<Comment> children;
}
