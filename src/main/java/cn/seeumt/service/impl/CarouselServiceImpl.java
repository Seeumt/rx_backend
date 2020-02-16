package cn.seeumt.service.impl;

import cn.seeumt.dataobject.Carousel;
import cn.seeumt.dao.CarouselMapper;
import cn.seeumt.service.CarouselService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-02
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;
    @Override
    public List<Carousel> getCarousels() {
        QueryWrapper<Carousel> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0);
        return carouselMapper.selectList(wrapper);
    }

    @Override
    public List<Carousel> getCarouselsByParentId(String parentId) {
        QueryWrapper<Carousel> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0).eq("parent_id",parentId);
        return carouselMapper.selectList(wrapper);
    }
}
