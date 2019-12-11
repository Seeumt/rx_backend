package cn.seeumt.enums;

import lombok.Getter;

/**
 * @author Seeumt
 * @date 2019/12/8 14:19
 */
@Getter
public enum Tips {
    ARTICLE_THUMB(1,"文章点赞"),
    ARTICLE_TRANSFER(2,"文章转发"),
    COMMENT_THUMB(3,"评论点赞"),
    POST_THUMB(4,"动态点赞"),
    ARTICLE_COMMENT(1,"文章的评论"),
    ARTICLE_COMMENT_COMMENT(2,"文章评论的评论"),
    POST_COMMENT(5,"动态的评论"),
    POST_COMMENT_COMMENT(6,"动态评论的评论"),
    ;


    private Integer code;

    private String msg;

    Tips(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
