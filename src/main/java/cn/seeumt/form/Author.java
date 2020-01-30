package cn.seeumt.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/29 21:58
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    /**
     * id : 1189
     * name : 开发者5
     * portrait : https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png
     */

    private int id;
    private String name;
    private String portrait;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
