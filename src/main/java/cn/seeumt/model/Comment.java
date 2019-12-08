package cn.seeumt.model;

import lombok.Data;

import java.util.Date;
@Data
public class Comment {
    private String id;

    private Byte type;

    private Date createTime;


    private Boolean enabled;


    private String commentId;


}
