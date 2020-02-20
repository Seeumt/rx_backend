package cn.seeumt.form;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/20 16:38
 */
@Data
public class Comment {
    private String commentId;
    private String apiRootId;
    private String parentId;
    private Byte type;
    private String userId;
    private String content;

}
