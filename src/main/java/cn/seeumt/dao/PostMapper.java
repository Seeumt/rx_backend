package cn.seeumt.dao;

import cn.seeumt.dataobject.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author Seeumt
 */
public interface PostMapper extends BaseMapper<Post> {
    /**
     * 通过主键删除
     * @param postId
     * @return
     */
    int deleteByPrimaryKey(String postId);

    @Override
    /***
     * 插入记录
     * @param record
     * @return int
     */
    int insert(Post record);

    /**
     * 选择性插入
     * @param record
     * @return int
     */
    int insertSelective(Post record);

    /**
     * 通过通过主键选择
     * @param postId
     * @return int
     */
    Post selectByPrimaryKey(String postId);

    /**
     * 选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Post record);

    /**
     * 通过主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Post record);

    /**
     * 批量查询所有关注的动态
     * @param idolUserIds 关注者id
     * @return List<Post>
     */
    List<Post> selectIdolPostsBatch(List<String> idolUserIds);
}
