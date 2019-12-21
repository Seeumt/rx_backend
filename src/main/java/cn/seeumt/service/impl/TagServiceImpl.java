package cn.seeumt.service.impl;

import cn.seeumt.dataobject.Tag;
import cn.seeumt.dao.TagMapper;
import cn.seeumt.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> findByTagIds(List<String> tagIds) {
        List<Tag> tags = tagMapper.selectBatchIds(tagIds);
        return tags;
    }
}
