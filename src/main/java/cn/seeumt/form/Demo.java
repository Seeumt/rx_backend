package cn.seeumt.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/29 21:57
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Demo {

    /**
     * author : {"id":1189,"name":"开发者5","portrait":"https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png"}
     * content : 支持一下
     * id : 296139614
     * pubDate : 2019-08-12 11:18:50
     * refer : [{"author":"开发者4","commentAuthor":{"id":1041792,"name":"开发者4","portrait":"https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png"},"content":"想入坑，但vue有点不熟，怕掉坑","pubDate":"2019-08-12 10:16:40"},{"author":"开发者2","commentAuthor":{"id":1041792,"name":"开发者","portrait":"https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png"},"content":"一直都在用uni-app开发移动端，真的很方便。","pubDate":"2019-08-12 10:11:40"},{"author":"uni-app","commentAuthor":{"id":4001233,"name":"uni-app","portrait":"https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png"},"content":"uni-app 是一个使用 Vue.js 开发所有前端应用的框架，开发者编写一套代码，可发布到iOS、Android、H5、以及各种小程序（微信/阿里/百度/头条/QQ）等多个平台。","pubDate":"2019-08-12 09:26:21"}]
     */

    /**
     * 作者个人信息
     *
     *      * id : 1189
     *      * name : 开发者5
     *      * portrait : https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png
     *
     */

    private Author author;
    private String content;
    private int id;
    private String pubDate;
    private List<Refer> refer;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public List<Refer> getRefer() {
        return refer;
    }

    public void setRefer(List<Refer> refer) {
        this.refer = refer;
    }
}
