package cn.seeumt.controller;

import cn.seeumt.service.LoveService;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Seeumt
 * @date 2019/12/8 13:12
 */
@RestController
@RequestMapping("/loves")
public class LoveController {
    @Autowired
    private LoveService loveService;
    @PostMapping("/{type}")
    public ResultVO addLove(@RequestParam("apiRootId") String apiRootId,
                            @RequestParam("userId") String userId,
                            @PathVariable("type")Integer type) {
        return loveService.addOrCancelLove(apiRootId, userId,type);
    }

    @PutMapping("/")
    public ResultVO changeLoveType(@RequestParam("apiRootId") String apiRootId,
                            @RequestParam("userId") String userId)
                            {
        return loveService.changeLoveType(apiRootId, userId, (byte) 3);
    }
}
