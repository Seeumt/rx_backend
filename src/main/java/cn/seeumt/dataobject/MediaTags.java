package cn.seeumt.dataobject;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class MediaTags implements Serializable {

    private static final long serialVersionUID = 6664434480377854121L;

    /**
     * id
     */
    @TableId
    private String id;

    /**
     * //对应的标签的id
     */
    private String tagId;

    /**
     * //父id
     */
    private String parentId;


}
