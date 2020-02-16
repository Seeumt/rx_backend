package cn.seeumt.dataobject;

import java.time.LocalDateTime;
import java.io.Serializable;

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
 * @since 2020-01-02
 */
@Data
public class Carousel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 轮播图 id
     */
    @TableId
    private String carouselId;

    /**
     * 所属id

     */
    private String parentId;

    /**
     * 用户 id
     */
    private String userId;

    private String imgUrl;

    private Boolean enabled;

    private Boolean deleted;

    /**
     * 展示排列顺序
     */
    private Integer sort;

    /**
     * 创建时间

     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
