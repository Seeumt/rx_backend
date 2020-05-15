package cn.seeumt.service;
import cn.seeumt.model.Category;
import cn.seeumt.vo.ResultVO;

import java.util.List;

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

    /**
     * 新建类目
     * @param category 表单类category
     * @return ResultVO
     */
    ResultVO create(cn.seeumt.form.Category category);

    /**
     * 删除类目
     * @param categoryId category表主键
     * @return ResultVO
     */
    ResultVO delete(Integer categoryId);

    /**
     * 更新类目名称
     * @param categoryId category表主键
     * @param name 新名称
     * @return ResultVO
     */
    ResultVO put(Integer categoryId, String name);

    /**
     * 返回树状结构的类别
     * @param categoryId 目录id
     * @return List<Category>
     */
    List<Category> getNextLevelCategory(Integer categoryId);

    /**
     * 返回树状结构的类别
     * @param categoryModelList 目录List
     * @param parentId 父级id
     * @return List<Category>
     */
    List<Category> listCategoryTree(List<Category> categoryModelList, Integer parentId);
}
