package cn.seeumt.service.impl;

import cn.seeumt.dao.CityMapper;
import cn.seeumt.dataobject.ArticleCities;
import cn.seeumt.dao.ArticleCitiesMapper;
import cn.seeumt.service.ArticleCitiesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
@Service
public class ArticleCitiesServiceImpl implements ArticleCitiesService {

    @Autowired
    private ArticleCitiesMapper articleCitiesMapper;
    @Override
    public List<String> findCityIdsByArticleId(String articleId) {
        QueryWrapper<ArticleCities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        List<ArticleCities> articleCities = articleCitiesMapper.selectList(queryWrapper);
        List<String> cityIds = articleCities.stream().map(articleCity -> articleCity.getCityId()).collect(Collectors.toList());
        return cityIds;
    }
}
