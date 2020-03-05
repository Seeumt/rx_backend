package cn.seeumt.dto;

import cn.seeumt.model.MyPageHelper;
import com.github.pagehelper.PageInfo;
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
    private MyPageHelper<PostDTO> myPageHelper;

    public PostListDataItem(Integer id, Set<PostDTO> posts) {
        this.id = id;
        this.posts = posts;
    }

    public PostListDataItem(Integer id, MyPageHelper<PostDTO> myPageHelper) {
        this.id = id;
        this.myPageHelper = myPageHelper;
    }
}
