package cn.seeumt.service;

import cn.seeumt.dataobject.Carousel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-02
 */
public interface CarouselService {
    /**
     * 轮播图接口
     * @return List<Carousel>
     */
    List<Carousel> getCarousels();

    /***
     * 根据父级id获取轮播图集合
     * @param parentId 父级id
     * @return List<Carousel>
     */
    List<Carousel> getCarouselsByParentId(String parentId);
}
