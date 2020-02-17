package cn.seeumt.service.impl;

import cn.seeumt.dataobject.Collect;
import cn.seeumt.dataobject.Rating;
import cn.seeumt.dao.RatingMapper;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.RatingService;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Seeumt
 * @since 2020-02-17
 */
@Service
public class RatingServiceImpl extends ServiceImpl<RatingMapper, Rating> implements RatingService {
    @Autowired
    private RatingMapper ratingMapper;

    @Override
    public ResultVO addOrCancelRating(String apiRootId, String userId, Integer rating) {
        Rating aRating = selectByApiRootIdAndUserId(apiRootId, userId);
        //如果没有点过赞 成功啦！点赞  改变love的status
        if (aRating==null) {
            Rating rating1 = new Rating();
            rating1.setRatingId(UuidUtil.getUUID());
            rating1.setRating(rating);
            rating1.setUserId(userId);
            rating1.setApiRootId(apiRootId);
            rating1.setStatus(true);
            rating1.setCreateTime(new Date());
            rating1.setUpdateTime(new Date());
            int insert = ratingMapper.insert(rating1);
            if (insert < 1) {
                throw new TipsException(TipsFlash.RATING_FAILED);
            }
            return ResultVO.success(0,"评分成功啦！",rating);
        }
        //如果点过了赞，再点就是取消
        else if (aRating.getStatus()) {
            if (aRating.getRating().equals(rating)) {
                aRating.setStatus(false);
                aRating.setUpdateTime(new Date());
                int i = ratingMapper.updateById(aRating);
                if (i == 1) {
                    return ResultVO.success(0,"评分取消啦！",0);
                } else {
                    throw new TipsException(TipsFlash.RATING_FAILED);
                }
            }else {
                aRating.setRating(rating);
                aRating.setUpdateTime(new Date());
                int i = ratingMapper.updateById(aRating);
                if (i == 1) {
                    return ResultVO.success(0,"评分成功啦！！！",rating);
                } else {
                    throw new TipsException(TipsFlash.RATING_FAILED);
                }
            }
        } else if (!aRating.getStatus()) {
            aRating.setStatus(true);
            aRating.setRating(rating);
            aRating.setUpdateTime(new Date());
            int i = ratingMapper.updateById(aRating);
            if (i == 1) {
                return ResultVO.success(0,"你又来评分啦？！",rating);
            } else {
                throw new TipsException(TipsFlash.COLLECT_FAILED);
            }

        }
        throw new TipsException(TipsFlash.COLLECT_FAILED);
    }

    @Override
    public Rating selectByApiRootIdAndUserId(String apiRootId, String userId) {
        QueryWrapper<Rating> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("api_root_id", apiRootId).eq("user_id", userId);
        return ratingMapper.selectOne(queryWrapper);
    }
}
