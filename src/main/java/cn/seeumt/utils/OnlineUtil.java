package cn.seeumt.utils;

import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.User;
import cn.seeumt.enums.Tips;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/17 11:23
 */
@Component
@Data
public class OnlineUtil {
    @Autowired
    public UserMapper userMapper;

    private static OnlineUtil onlineUtil;

    @PostConstruct
    public void init() {
        onlineUtil = this;
        onlineUtil.userMapper = this.userMapper;
    }

    public static void setLastOperateTime(String userId) {
        User user = OnlineUtil.onlineUtil.userMapper.selectById(userId);
        user.setLastVisitTime(new Date());
        int i = OnlineUtil.onlineUtil.userMapper.updateById(user);
        if (i < 1) {
            throw new TipsException(TipsFlash.UPDATE_LAST_VISIT_TIME_FAILED);
        }
    }

    public static void setLastOperateTimeByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = OnlineUtil.onlineUtil.userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new TipsException(TipsFlash.QUERY_USER_FAILED);
        }
        user.setLastVisitTime(new Date());
        int i = OnlineUtil.onlineUtil.userMapper.updateById(user);
        if (i < 1) {
            throw new TipsException(TipsFlash.UPDATE_LAST_VISIT_TIME_FAILED);
        }
    }

    public static void setLastOperateTimeByUserId(String userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        User user = OnlineUtil.onlineUtil.userMapper.selectOne(queryWrapper);
        user.setLastVisitTime(new Date());
        int i = OnlineUtil.onlineUtil.userMapper.updateById(user);
        if (i < 1) {
            throw new TipsException(TipsFlash.UPDATE_LAST_VISIT_TIME_FAILED);
        }
    }


}
