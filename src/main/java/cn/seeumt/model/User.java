package cn.seeumt.model;

import cn.seeumt.dataobject.Comment;
import lombok.Data;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 15:48
 */
@Data
public class User {
    private String id;

    private String nickname;

    private String faceIcon;

    private Byte enabled;

    List<Comment> comments;


}
