package cn.seeumt.controller;


import cn.seeumt.enums.TipsFlash;
import cn.seeumt.form.Follow;
import cn.seeumt.service.FollowService;
import cn.seeumt.utils.OnlineUtil;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 关注
 * @author Seeumt
 * @since 2020-02-10
 */
@Api(tags = {"关注"})
@RestController
@RequestMapping("/follows")
@Slf4j
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class FollowController {
    @Autowired
    private FollowService followService;

    /**
     * 关注用户
     * @param follow 关注用户请求体
     * @return ResultVO
     */
    @PostMapping("/")
    public ResultVO follow(@RequestBody Follow follow) {
        OnlineUtil.setLastOperateTimeByUserId(follow.getUserId());
        log.info("用户 {}关注用户 {}",follow.getUserId(),follow.getUserId());
        return followService.add(follow.getUserId(), follow.getIdolId());
    }

    /**
     * 是否已关注
     * @param idolId 爱豆用户主键id
     * @param userId 用户主键id
     * @return
     */
    @GetMapping("/{idolId}")
    public ResultVO isIdol(@PathVariable("idolId") String idolId,
                            String userId) {
        if ("".equals(idolId)) {
            return ResultVO.error(TipsFlash.NULL_ARGUMENT);
        }else{
            if ("".equals(userId)) {
                return ResultVO.success(false);
            }
            return followService.isIdol(idolId, userId);
        }
    }


}
