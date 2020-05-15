package cn.seeumt.controller;


import cn.seeumt.model.Category;
import cn.seeumt.service.CategoryService;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类别
 * @author Seeumt
 * @since 2020-01-30
 */
@Api(tags = {"类别"})
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 得到某一父类别下的树状子类型
     * @param parentId 父类别的类别主键id
     * @return ResultVO
     */
    @GetMapping("/tree/{parentId}")
    public ResultVO getCategoryTree(@PathVariable Integer parentId) {
        List<Category> nextLevelCategory = categoryService.getNextLevelCategory(parentId);
        List<Category> categoryTree = categoryService.listCategoryTree(nextLevelCategory,parentId);
        return ResultVO.success(categoryTree);
    }

    /**
     * 添加新类别
     * @param category 类别请求体
     * @return ResultVO
     */
    @PostMapping("/")
    public ResultVO createCategory(@RequestBody cn.seeumt.form.Category category) {
        return categoryService.create(category);
    }

    /**
     * 删除某一类别
     * @param categoryId 类别主键id
     * @return ResultVO
     */
    @DeleteMapping("/{categoryId}")
    public ResultVO deleteCategory(@PathVariable Integer categoryId) {
        return categoryService.delete(categoryId);
    }

    /**
     * 更新某一类别名称
     * @param categoryId 类别主键id
     * @param name 新类别名称
     * @return ResultVO
     */
    @PutMapping("/")
    public ResultVO putCategory(Integer categoryId,String name) {
        return categoryService.put(categoryId,name);
    }

}
