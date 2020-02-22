package cn.seeumt.service.impl;

import cn.seeumt.dataobject.MediaTags;
import cn.seeumt.dao.MediaTagsMapper;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.MediaTagsService;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Seeumt
 * @since 2019-12-21
 */
@Service
public class MediaTagsServiceImpl implements MediaTagsService {
    @Autowired
    private MediaTagsMapper mediaTagsMapper;

    @Override
    public List<String> findTagIdsByParentId(String parentId) {
        QueryWrapper<MediaTags> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<MediaTags> mediaTags = mediaTagsMapper.selectList(queryWrapper);
//        List<String> tagIds = mediaTags.stream().sorted((at1,at2)->Integer.parseInt(at1.getTagId())-Integer.parseInt(at2.getTagId())).map(articleTag -> articleTag.getTagId()).collect(Collectors.toList());
        List<String> tagIds = mediaTags.stream().sorted(Comparator.comparing(MediaTags::getTagId)).map(mediaTag -> mediaTag.getTagId()).collect(Collectors.toList());
        return tagIds;
    }

    @Override
    public void insert(List<String> tagIdList, String parentId) {
        for (String tagId : tagIdList) {
            String id = UuidUtil.getUuid();
            MediaTags mediaTags = new MediaTags(id, tagId, parentId);
            int insert = mediaTagsMapper.insert(mediaTags);
            if (insert < 1) {
                throw new TipsException(TipsFlash.INSERT_MEDIA_TAGS_FAILED);
            }
        }
    }




}
