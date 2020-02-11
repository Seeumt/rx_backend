package cn.seeumt.controller;


import cn.seeumt.form.Follow;
import cn.seeumt.service.FollowService;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/follows")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping("/")
    public ResultVO follow(@RequestBody Follow follow) {
        return followService.add(follow.getUserId(), follow.getIdolId());
    }


}
