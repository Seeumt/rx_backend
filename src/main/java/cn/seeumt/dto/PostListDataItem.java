package cn.seeumt.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/10 19:23
 */
@Data
@AllArgsConstructor
public class PostListDataItem {
    /**
     * 0-已关注列表 1-推荐列表
     */
    private Integer id;
    private Set<PostDTO> posts;
}
