package cn.seeumt.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Collect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String collectId;

    /**
     * //1代表文章点赞 2代表文章转发 3代表评论点赞 4代表回复点赞
     */
    private Integer type;

    /**
     * //0代表取消赞 1代表有效赞  //0代表有效转发 1代表已删除转发
     */
    private Boolean status;

    private Date createTime;

    private Date updateTime;

    /**
     * //1代表点赞 2代表点踩
     */
    private Boolean enabled;

    private String userId;

    private String apiRootId;


}
