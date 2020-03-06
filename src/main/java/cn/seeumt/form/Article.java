package cn.seeumt.form;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Seeumt
 * @since 2019-12-21
 */
@Data
public class Article implements Serializable {


    private static final long serialVersionUID = 2894800192934033466L;
    /**
     * //文章id
     */
    private String articleId;

    /**
     * //标题
     */
    private String title;

    /**
     * //html内容
     */
    private String htmlContent;

    /**
     * //封面图片
     */
    private String coverPicture;

    /**
     * //用户id
     */
    private String userId;

    public static void main(String[] args) {
        System.out.println();
    }
}
