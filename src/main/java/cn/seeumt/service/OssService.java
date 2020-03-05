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
    /**
     * 保存
     * @param originUrl 服务器返回资源url
     * @param parentId 父id
     * @return String
     */
    String saveOss(String originUrl, String parentId);

    /**
     * 保存评论图片（原始url）
     * @param originUrl 服务器返回资源url
     * @param parentId 父id
     */
    void saveOssForComment(String originUrl, String parentId);

    /**
     * 根据父id查询资源
     * @param parentId 父id
     * @return ImgDTO
     */
    ImgDTO queryByParentId(String parentId);

    /**
     * 通过资源id删除资源
     * @param ossId 资源id
     * @return ResultVO
     */
    ResultVO deleteByOssId(String ossId);

    String saveOssForMedia(String originUrl, String parentId,Integer type);
}
