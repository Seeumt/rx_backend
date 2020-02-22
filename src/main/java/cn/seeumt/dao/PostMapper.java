package cn.seeumt.dao;

import cn.seeumt.dataobject.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface PostMapper extends BaseMapper<Post> {
    int deleteByPrimaryKey(String postId);

    @Override
    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(String postId);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
}
