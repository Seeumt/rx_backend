package cn.seeumt.controller;


import cn.seeumt.dataobject.Carousel;
import cn.seeumt.service.CarouselService;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 轮播图
 * @author Seeumt
 * @since 2020-01-02
 */
@Api(tags = {"轮播图"})
@RestController
@RequestMapping("/carousels")
@Slf4j
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    /**
     * 得到所有轮播图
     * @return ResultVO
     */
    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    private ResultVO carousels() {
        List<Carousel> carousels = carouselService.getCarousels();
        return ResultVO.success(carousels);
    }

    /**
     * 查询某一纪念品下的所有轮播图
     * @param parentId souvenirId
     * @return ResultVO
     */
    @GetMapping("/{parentId}")
    private ResultVO find(@PathVariable("parentId")String parentId) {
        log.info("【轮播图】查看{}详情",parentId);
        List<Carousel> carousels = carouselService.getCarouselsByParentId(parentId);
        return ResultVO.success(carousels);
    }


}
