package cn.seeumt.service;

import cn.seeumt.vo.ResultVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
public interface MediaTagsService {

    /**
     * 找到某一id下的所有标签id
     * @param parentId 父级id
     * @return List<String>
     */
    List<String> findTagIdsByParentId(String parentId);

    /**
     * 插入标签为某id(article,post)
     * @param tagIdList 标签ids
     * @param parentId articleId,postId
     */
    void insert(List<String> tagIdList,String parentId);


}
