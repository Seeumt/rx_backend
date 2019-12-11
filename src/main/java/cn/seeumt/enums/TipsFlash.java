package cn.seeumt.enums;

import lombok.Getter;

/**
 * @author Seeumt
 * @date 2019/12/9 20:34
 */
@Getter
public enum TipsFlash {
    ARTICLE_INSERT_FAILED(10001, "新建文章失败"),
    ARTICLE_THUMB_FAILED(10002, "文章点赞失败"),
    ABNORMAL_THUMB(10003, "刚刚好像有外星人给你点赞了"),
    ;
    private Integer code;
    private String msg;

    TipsFlash(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
