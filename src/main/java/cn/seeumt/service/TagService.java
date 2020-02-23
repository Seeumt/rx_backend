package cn.seeumt.service;

import cn.seeumt.dataobject.Tag;
import cn.seeumt.vo.ResultVO;
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
    /**
     * 批量查询标签
     * @param tagIds 标签id集合
     * @return List<TagVO>
     */
    List<TagVO> findTagVoByTagIds(List<String> tagIds);

    /**
     * 查询数据库所有标签
     * @return List<TagVO>
     */
    ResultVO get();
}
