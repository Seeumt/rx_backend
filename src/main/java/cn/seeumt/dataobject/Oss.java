package cn.seeumt.dataobject;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-08
 */
@Data
public class Oss {

    /**
     * 图片id
     */
    private String ossId;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 默认为1表示通过
     */
    private Boolean enabled;

    /**
     * 默认为0表示未删除
     */
    private Boolean deleted;

    /**
     * 父级id
     */
    private String parentId;


}
