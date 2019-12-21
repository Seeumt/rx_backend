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
 * @since 2019-12-21
 */
@Data
public class Tag implements Serializable {


    private static final long serialVersionUID = -3311632248998935570L;
    /**
     * //标签id
     */
    @TableId
    private String tagId;

    /**
     * //标签名称
     */
    private String name;

    /**
     * //创建时间
     */
    private LocalDateTime createTime;

    /**
     * //更新时间
     */
    private LocalDateTime updateTime;

    /**
     * //是否可用
     */
    private Boolean enabled;

    /**
     * //是否删除
     */
    private Boolean deleted;

    /**
     * //添加此标签的管理员id
     */
    private String adminId;


}
