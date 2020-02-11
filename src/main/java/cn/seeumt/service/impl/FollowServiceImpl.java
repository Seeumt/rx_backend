package cn.seeumt.service.impl;
import java.util.Date;

import cn.seeumt.dataobject.Follow;
import cn.seeumt.dao.FollowMapper;
import cn.seeumt.service.FollowService;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2020-02-10
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Override
    public List<Follow> getAllIdol(String userId) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("is_follow", true);
        return followMapper.selectList(wrapper);
    }

    @Override
    public ResultVO add(String userId, String idolId) {
        Follow follow = new Follow();
        follow.setFollowId(UuidUtil.getUUID());
        follow.setUserId(userId);
        follow.setIdolId(idolId);
        follow.setIsFollow(true);
        follow.setCreateTime(new Date());
        follow.setUpdateTime(new Date());
        if (followMapper.insert(follow) < 0) {
            return ResultVO.error(147, "关注失败");
        }
        return ResultVO.success(123, "关注成功！");
    }
}
