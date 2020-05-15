package cn.seeumt.controller;

import cn.seeumt.service.LoveService;
import cn.seeumt.utils.OnlineUtil;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 点赞
 * @author Seeumt
 * @date 2019/12/8 13:12
 */
@Api(tags = {"点赞"})
@RestController
@RequestMapping("/loves")
public class LoveController {
    @Autowired
    private LoveService loveService;

    /**
     * 点赞功能
     * @param apiRootId 某根级id
     * @param userId 用户id
     * @param type 类型
     * @return ResultVO
     */
    @PostMapping("/{type}")
    public ResultVO addLove(@RequestParam("apiRootId") String apiRootId,
                            @RequestParam("userId") String userId,
                            @PathVariable("type")Integer type) {
        OnlineUtil.setLastOperateTimeByUserId(userId);
        return loveService.addOrCancelLove(apiRootId, userId,type);
    }

    /**
     * 点赞/点踩
     * @param apiRootId 某rootId
     * @param userId 用户主键Id
     * @return ResultVO
     */
    @PutMapping("/")
    public ResultVO changeLoveType(@RequestParam("apiRootId") String apiRootId,
                            @RequestParam("userId") String userId){
        OnlineUtil.setLastOperateTimeByUserId(userId);
        return loveService.changeLoveType(apiRootId, userId, (byte) 3);
    }
}
