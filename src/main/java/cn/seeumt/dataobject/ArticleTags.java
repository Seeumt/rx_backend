package cn.seeumt.dataobject;

import java.io.Serializable;
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
public class ArticleTags implements Serializable {

    private static final long serialVersionUID = 6664434480377854121L;

    /**
     * id
     */
    private String id;

    /**
     * //文章对应的标签的id
     */
    private String tagId;

    /**
     * //文章的id
     */
    private String articleId;


}
