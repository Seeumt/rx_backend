package cn.seeumt.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/29 21:58
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Refer {
    /**
     * author : 开发者4
     * commentAuthor : {"id":1041792,"name":"开发者4","portrait":"https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png"}
     * content : 想入坑，但vue有点不熟，怕掉坑
     * pubDate : 2019-08-12 10:16:40
     */

    private String author;
    private CommentAuthor commentAuthor;
    private String content;
    private String pubDate;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CommentAuthor getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(CommentAuthor commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
