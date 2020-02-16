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

    List<String> findTagIdsByParentId(String parentId);

    void insert(List<String> tagIdList,String parentId);


}
