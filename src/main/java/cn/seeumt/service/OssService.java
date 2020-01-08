package cn.seeumt.service;

import cn.seeumt.dataobject.Oss;
import cn.seeumt.model.Img;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    List<Img> queryByParentId(String parentId);
}
