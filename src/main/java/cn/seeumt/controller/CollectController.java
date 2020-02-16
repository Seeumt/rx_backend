package cn.seeumt.controller;


import cn.seeumt.dataobject.Collect;
import cn.seeumt.service.CollectService;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-16
 */
@RestController
@RequestMapping("/collects")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @PostMapping("/")
    public ResultVO addOrCancelCollect(@RequestParam("apiRootId") String apiRootId,
                            @RequestParam("userId") String userId) {
        return collectService.addOrCancelCollect(apiRootId, userId);
    }
    @GetMapping("/{apiRootId}")
    public ResultVO isCollect(@PathVariable("apiRootId") String apiRootId,
                                       @RequestParam("userId") String userId) {
        Collect collect = collectService.selectByApiRootIdAndUserId(apiRootId, userId);
        if (collect == null) {
            return ResultVO.success(true);
        }
        return ResultVO.success(false);
    }


}
