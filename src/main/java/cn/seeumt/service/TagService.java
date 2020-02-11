package cn.seeumt.service;

import cn.seeumt.dataobject.Tag;
import cn.seeumt.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
public interface TagService{
    List<TagVO> findTagVOByTagIds(List<String> tagIds);

}
