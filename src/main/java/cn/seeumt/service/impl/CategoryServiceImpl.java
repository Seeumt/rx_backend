package cn.seeumt.service.impl;

import cn.seeumt.dataobject.ArticleCities;
import cn.seeumt.dataobject.Category;
import cn.seeumt.dao.CategoryMapper;
import cn.seeumt.dataobject.Souvenir;
import cn.seeumt.enums.TipsBusiness;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.CategoryService;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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


}
