package cn.seeumt.enums;

import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.Getter;

/**
 * @author Seeumt
 * @date 2019/12/9 20:34
 */
@Getter
public enum TipsFlash implements CodeEnum {
    /**
     * 10001--新建文章失败
     */
    ARTICLE_INSERT_FAILED(10001, "新建文章失败"),
    ARTICLE_THUMB_FAILED(10002, "文章点赞失败"),
    ABNORMAL_THUMB(10003, "刚刚好像有外星人给你点赞了"),
    TH_FAILED(10004, "点赞点踩系统报错了哟"),
    QUERY_ARTICLE_FAILED(10005, "查询文章失败"),
    ADD_TO_CART_FAILED(70001, "添加到购物车失败"),
    ADD_TO_ORDER_MASTER_FAILED(60001, "添加到主订单失败"),
    ADD_TO_ORDER_DETAIL_FAILED(60002, "添加到子订单失败"),
    INVAILD_ARGUMENT(70002, "参数不合法"),
    NULL_ARGUMENT(70003, "参数为空"),
    CLEAN_CART_FAILED(70004, "清空购物车失败"),
    CATEGORY_QUERY_FAILED(80001, "目录查询失败"),
    DELETED_FAILED(10008, "删除失败"),
    INSERT_MEDIA_TAGS_FAILED(5001, "插入标签失败"),
    INSERT_POST_FAILED(30001, "新建动态失败"),
    DELETE_OSS_FAILED(30002, "删除OSS失败"),
    TELEPHONE_NOT_RECORDED(10019, "该手机号无记录"),
    TELEPHONE_HAS_BOUND(10009, "该手机号已被绑定"),
    UPDATE_POST_CONTENT(30003, "更新评论内容失败"),
    INSERT_COMMENT_FAILED(30004, "插入评论失败"),
    INSERT_COMMENT_PIC_FAILED(30005, "插入评论图片失败"),
    COLLECT_FAILED(40003, "收藏失败"),
    RATING_FAILED(40005, "评分失败"),
    UPDATE_LAST_VISIT_TIME_FAILED(10009, "更新用户最近访问时间失败"),
    QUERY_USER_FAILED(10010, "查询用户失败"),
    QUERY_TARGET_COMMENT_FAILED(50009, "查询父级评论失败"),
    BIND_TELEPHONE_EXCEPTION(10011, "绑定手机号异常"),
    FIND_WX_USER_EXCEPTION(10012, "查找微信用户异常"),
    FIND_USER_EXCEPTION(10015, "查找用户异常,微信未绑定"),
    UPDATE_USER_EXCEPTION(10016, "更新用户异常"),
    UPDATE_USER_PASSWORD_EXCEPTION(10026, "更改用户密码异常"),
    UPDATE_WX_USER_EXCEPTION(10018, "更新微信用户异常"),
    SEND_WELCOME_MSG_EXCEPTION(10020, "发送密码短信异常"),
    VALID_OTPCODE_SUCCESS(10021, "验证码校验成功"),
    PIC_CONVERT_FAILED(10056, "图片转化成流失败"),
    INSERT_CATEGORY_FAILED(70003, "增加新类目失败"),
    CATEGORY_NAME_EXISTED(70005, "增加新类目失败"),
    DELETE_CATEGORY_FAILED(70007, "删除类目失败"),
    CATEGORY_NOT_EXISTED(70009, "该类目不存在"),
    UPDATE_CATEGORY_FAILED(70011, "更新类目失败"),

    ;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;

    TipsFlash(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
