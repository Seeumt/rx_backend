package cn.seeumt.service.impl;
import java.time.LocalDateTime;

import cn.seeumt.dataobject.Category;
import cn.seeumt.dao.CategoryMapper;
import cn.seeumt.enums.Tips;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.CategoryService;
import cn.seeumt.utils.TreeUtil;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-30
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResultVO selectCategoryTreeByCategoryId(Integer categoryId) {
        QueryWrapper<Category> categoryWrapper = new QueryWrapper<>();
        categoryWrapper.eq("parent_id", categoryId);
        List<Category> categories = categoryMapper.selectList(categoryWrapper);
        if (CollectionUtils.isEmpty(categories)) {
            throw new TipsException(TipsFlash.CATEGORY_QUERY_FAILED);
        }
        List<Integer> categoryIds = categories.stream().map(Category::getCategoryId).collect(Collectors.toList());
        return null;
    }

    @Override
    public ResultVO create(cn.seeumt.form.Category category) {

        Category category1 = new Category();
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("name", category.getName());
        Category one = categoryMapper.selectOne(categoryQueryWrapper);
        if (one != null) {
            throw new TipsException(TipsFlash.CATEGORY_NAME_EXISTED);
        }
        BeanUtils.copyProperties(category, category1);
        category1.setStatus(true);
        category1.setCreateTime(LocalDateTime.now());
        category1.setUpdateTime(LocalDateTime.now());
        int insert = categoryMapper.insert(category1);
        if (insert > 0) {
            return ResultVO.success(Tips.INSERT_CATEGORY_SUCCESS);
        }
        throw new TipsException(TipsFlash.INSERT_CATEGORY_FAILED);
    }

    @Override
    public ResultVO delete(Integer categoryId) {
        int i = categoryMapper.deleteById(categoryId);
        if (i > 0) {
            return ResultVO.success(Tips.DELETE_CATEGORY_SUCCESS);
        }
        throw new TipsException(TipsFlash.DELETE_CATEGORY_FAILED);
    }

    @Override
    public ResultVO put(Integer categoryId, String name) {
        Category category = categoryMapper.selectById(categoryId);
        if (category == null) {
            throw new TipsException(TipsFlash.CATEGORY_NOT_EXISTED);
        }
        category.setName(name);
        int i = categoryMapper.updateById(category);
        if (i > 0) {
            return ResultVO.success(Tips.UPDATE_CATEGORY_SUCCESS);
        }
        throw new TipsException(TipsFlash.UPDATE_CATEGORY_FAILED);
    }

    @Override
    public List<cn.seeumt.model.Category> getNextLevelCategory(Integer categoryId) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", categoryId);
        List<Category> categories = categoryMapper.selectList(wrapper);
        return categories.stream().map(category -> {
            cn.seeumt.model.Category category1 = new cn.seeumt.model.Category();
            BeanUtils.copyProperties(category, category1);
            return category1;
        }).collect(Collectors.toList());

    }

    @Override
    public List<cn.seeumt.model.Category> listCategoryTree(List<cn.seeumt.model.Category> categoryModelList, Integer parentId) {
        return TreeUtil.listToCategoryTree(categoryModelList, parentId);
    }



}
