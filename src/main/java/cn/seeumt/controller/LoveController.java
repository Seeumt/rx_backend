package cn.seeumt.controller;

import cn.seeumt.service.LoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Seeumt
 * @date 2019/12/8 13:12
 */
@Controller
@RequestMapping("/love")
public class LoveController {

    @Autowired
    private LoveService loveService;

    @PostMapping(value = "/")
    @ResponseBody
    public int addLove(@RequestParam("uniqueId") String apiRootId,
                        @RequestParam("userId") String userId) {
        int i = loveService.addLove(apiRootId, userId);
        return i;
    }
}
