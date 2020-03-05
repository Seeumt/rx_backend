package cn.seeumt.enums;

import lombok.Getter;

/**
 * @author Seeumt
 * @date 2019/12/8 14:19
 */
@Getter
public enum Tips implements CodeEnum {
    /**
     *
     */
    ARTICLE_THUMB(1,"文章点赞"),
    ARTICLE_TRANSFER(2,"文章转发"),
    POST(3,"动态"),
    POST_THUMB(3,"like"),
    POST_HATE(4,"hate"),
    ARTICLE_COMMENT(1,"文章的评论"),
    COMMENT_THUMB(4,"评论点赞"),
    ARTICLE_COMMENT_COMMENT(2,"文章评论的评论"),
    POST_COMMENT(5,"动态的评论"),
    POST_COMMENT_COMMENT(6,"动态评论的评论"),
    IMAGE(1,"图片"),
    INSERT_FAIL(10001,"用户注册失败"),
    THIRD_OAUTH_FAIL(10002, "第三方授权登录失败"),
    WEIXIN(20001, "weixin"),
    WEIBO(20002,"weibo"),
    QQ(20003,"qq"),
    DELETED_SUCCESS(10007, "删除成功"),
    OTP_CODE_EXPIRED(10008, "验证码已过期"),
    OTP_CODE_ERROR(10009, "验证码错误"),
    BIND_SUCCESS(10010, "绑定成功"),
    DEFAULT_TEL(10050, "none"),
    SENDSMS_NORMAL(0, "普通短信"),
    SENDSMS_WEL(1, "带密码短信"),
    NO_IDOL(50002, "亲还没有关注任何人"),
    ;

    /**
     * 标识码
     */
    private Integer code;
    /**
     * 信息
     */
    private String msg;

    Tips(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
