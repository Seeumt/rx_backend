package cn.seeumt.form;

import cn.seeumt.vo.TagVO;
import cn.seeumt.vo.UserVO;
import lombok.Data;

import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/14 14:36
 */
@Data
public class Post {
    private String userId;
    private String content;
    private Boolean type;
    private String tagIds;
    private String location;
}
