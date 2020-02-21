package cn.seeumt.service;

import cn.seeumt.dataobject.Oss;
import cn.seeumt.dto.ImgDTO;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-08
 */
public interface OssService extends IService<Oss> {
    String saveOss(String originUrl, String parentId);

    void saveOssForComment(String originUrl, String parentId);

    ImgDTO queryByParentId(String parentId);

    ResultVO deleteByOssId(String ossId);
}
