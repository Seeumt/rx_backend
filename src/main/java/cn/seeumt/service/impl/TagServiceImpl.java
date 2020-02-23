package cn.seeumt.service.impl;

import cn.seeumt.dataobject.Tag;
import cn.seeumt.dao.TagMapper;
import cn.seeumt.service.TagService;
import cn.seeumt.vo.ResultVO;
import cn.seeumt.vo.TagVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<TagVO> findTagVoByTagIds(List<String> tagIds) {
        List<Tag> tags = tagMapper.selectBatchIds(tagIds);
        List<TagVO> tagVos = new ArrayList<>();
        for (Tag tag : tags) {
            TagVO tagVO = new TagVO();
            BeanUtils.copyProperties(tag,tagVO);
            tagVO.setIsSelected(false);
            tagVos.add(tagVO);
        }
        return tagVos;
    }

    @Override
    public ResultVO get() {
        List<TagVO> tagVos = tagMapper.selectList(null).stream().map(tag -> {
            TagVO tagVO = new TagVO();
            BeanUtils.copyProperties(tag, tagVO);
            return tagVO;
        }).collect(Collectors.toList());
        return ResultVO.success(tagVos);
    }

}
