package cn.seeumt.service;

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
}
