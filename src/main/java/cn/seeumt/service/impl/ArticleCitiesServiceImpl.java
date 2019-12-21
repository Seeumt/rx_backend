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
        List<String> cityIds = new ArrayList<>();
        for (ArticleCities articleCity : articleCities) {
            cityIds.add(articleCity.getCityId());
        }
        return cityIds;
    }
}
