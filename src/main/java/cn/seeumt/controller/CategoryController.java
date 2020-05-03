package cn.seeumt.controller;


import cn.seeumt.model.Category;
import cn.seeumt.service.CategoryService;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-30
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/tree/{parentId}")
    public ResultVO getCategoryTree(@PathVariable Integer parentId) {
        List<Category> nextLevelCategory = categoryService.getNextLevelCategory(parentId);
        List<Category> categoryTree = categoryService.listCategoryTree(nextLevelCategory,parentId);
        return ResultVO.success(categoryTree);
    }

    @PostMapping("/")
    public ResultVO createCategory(@RequestBody cn.seeumt.form.Category category) {
        return categoryService.create(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResultVO deleteCategory(@PathVariable Integer categoryId) {
        return categoryService.delete(categoryId);
    }

    @PutMapping("/")
    public ResultVO putCategory(Integer categoryId,String name) {
        return categoryService.put(categoryId,name);
    }

}
