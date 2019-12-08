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
    ARTICLE_COMMENT(1,"文章的评论"),
    COMMENT_COMMENT(2,"评论的评论")
    ;


    private Integer code;

    private String msg;

    Tips(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
