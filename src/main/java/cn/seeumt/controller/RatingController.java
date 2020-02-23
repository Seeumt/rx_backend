package cn.seeumt.controller;


import cn.seeumt.dataobject.Collect;
import cn.seeumt.dataobject.Rating;
import cn.seeumt.service.RatingService;
import cn.seeumt.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Seeumt
 * @since 2020-02-17
 */
@RestController
@RequestMapping("/ratings")
@Slf4j
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("/{apiRootId}")
    public ResultVO isCollect(@PathVariable("apiRootId") String apiRootId,
                              @RequestParam("userId") String userId,
                              @RequestParam("rating") Integer rating) {
        log.info("【评价】用户 {}给{}评了{}分",userId,apiRootId,rating);
        return ratingService.addOrCancelRating(apiRootId, userId,rating);
    }

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
