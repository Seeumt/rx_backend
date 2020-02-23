package cn.seeumt.service;

import cn.seeumt.dataobject.Category;
import cn.seeumt.dataobject.Souvenir;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-30
 */
public interface CategoryService{

    /**
     * 根据根目录id查询所有树状子级目录
     * @param categoryId 目录id
     * @return ResultVO
     */
    ResultVO selectCategoryTreeByCategoryId(Integer categoryId);

}
