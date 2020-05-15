package cn.seeumt.controller;


import cn.seeumt.dataobject.Collect;
import cn.seeumt.service.CollectService;
import cn.seeumt.utils.OnlineUtil;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏
 * @author Seeumt
 * @since 2020-02-16
 */
@Api(tags = {"收藏"})
@RestController
@RequestMapping("/collects")
@Slf4j
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = {"*"})
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 收藏或取消收藏
     * @param apiRootId 纪念品id/文章id/动态id
     * @param userId 用户主键id
     * @return ResultVO
     */
    @PostMapping("/")
    public ResultVO addOrCancelCollect(@RequestParam("apiRootId") String apiRootId,
                            @RequestParam("userId") String userId) {
        OnlineUtil.setLastOperateTime(userId);
        return collectService.addOrCancelCollect(apiRootId, userId);
    }

    /**
     * 是否已经收藏
     * @param apiRootId 纪念品id/文章id/动态id
     * @param userId 用户主键id
     * @return ResultVO
     */
    @GetMapping("/{apiRootId}")
    public ResultVO isCollect(@PathVariable("apiRootId") String apiRootId,
                                       @RequestParam("userId") String userId) {
        Collect collect = collectService.selectByApiRootIdAndUserId(apiRootId, userId);
        if (collect == null) {
            return ResultVO.success(false);
        }
        return ResultVO.success(true);
    }


}
