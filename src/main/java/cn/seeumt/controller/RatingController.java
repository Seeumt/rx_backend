package cn.seeumt.controller;


import cn.seeumt.dataobject.Collect;
import cn.seeumt.dataobject.Rating;
import cn.seeumt.service.RatingService;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评分
 * @author Seeumt
 * @since 2020-02-17
 */
@Api(tags = {"评分"})
@RestController
@RequestMapping("/ratings")
@Slf4j
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = {"*"})
public class RatingController {
    @Autowired
    private RatingService ratingService;

    /**
     * 用户评价
     * @param apiRootId 某一主键id
     * @param userId 用户id
     * @param rating 评分分数
     * @return ResultVO
     */
    @PostMapping("/{apiRootId}")
    public ResultVO isCollect(@PathVariable("apiRootId") String apiRootId,
                              @RequestParam("userId") String userId,
                              @RequestParam("rating") Integer rating) {
        log.info("【评价】用户 {}给{}评了{}分",userId,apiRootId,rating);
        return ratingService.addOrCancelRating(apiRootId, userId,rating);
    }

    /**
     * 是否已经收藏
     * @param apiRootId 某一主键id
     * @param userId 用户主键id
     * @return ResultVO
     */
    @GetMapping("/{apiRootId}")
    public ResultVO isCollect(@PathVariable("apiRootId") String apiRootId,
                              @RequestParam("userId") String userId) {
        Rating rating = ratingService.selectByApiRootIdAndUserId(apiRootId, userId);
        if (rating == null) {
            return ResultVO.success(0);
        }
        return ResultVO.success(rating.getRating())     ;
    }
}
