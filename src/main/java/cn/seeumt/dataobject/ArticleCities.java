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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ArticleCities implements Serializable {

    private static final long serialVersionUID = 6220958101570373349L;
    /**
     * id
     */
    private String id;

    /**
     * 城市id
     */
    private String cityId;

    /**
     * 文章id
     */
    private String articleId;


}
