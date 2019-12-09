package cn.seeumt.model;

import lombok.Data;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 20:31
 */
@Data
public class Commenter {

    private String userId;

    private String nickname;

    private String faceIcon;

    private String loveId;

    List<CommentContent> commentContents;

}
