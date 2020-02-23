package cn.seeumt.service.impl;

import cn.seeumt.dao.LoveMapper;
import cn.seeumt.dataobject.Love;
import cn.seeumt.enums.Tips;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.LoveService;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 12:28
 */
@Service
@Slf4j
public class LoveServiceImpl implements LoveService {


    @Autowired
    private LoveMapper loveMapper;

    @Override
    public ResultVO addOrCancelLove(String apiRootId, String userId, Integer type) {
        Love aLove = selectByApiRootIdAndUserIdAndType(apiRootId, userId, (byte) 3);
        //如果没有点过赞 成功啦！点赞  改变love的status
        if (aLove==null) {
            log.info("用户 {}点赞了{}",userId,apiRootId);
            Love love = new Love();
            love.setLoveId(UuidUtil.getUuid());
            love.setStatus(true);
            love.setCreateTime(new Date());
            love.setUpdateTime(new Date());
            love.setUserId(userId);
            love.setApiRootId(apiRootId);
            love.setContent(null);
            if (Tips.POST_THUMB.getCode().equals(type)) {
                love.setType((byte)(Tips.POST_THUMB.getCode().intValue()));
                love.setEnabled(true);
                love.setLoveType(Tips.POST_THUMB.getMsg());
            }else {
                log.info("用户 {}点踩了{}",userId,apiRootId);
                love.setType((byte)(Tips.POST_HATE.getCode().intValue()));
                love.setEnabled(false);
                love.setLoveType(Tips.POST_HATE.getMsg());
            }
            int insert = loveMapper.insert(love);
            if (insert < 1) {
                throw new TipsException(TipsFlash.TH_FAILED);
            }
            return ResultVO.success("成功啦！");
        }
        //如果点过了赞，再点就是取消
        else if (aLove.getStatus()) {
            log.info("用户 {}取消点赞|点踩 {}",userId,apiRootId);
            aLove.setStatus(false);
            aLove.setUpdateTime(new Date());
            int i = loveMapper.updateById(aLove);
            if (i == 1) {
                return ResultVO.success("成功啦！");
            } else {
                throw new TipsException(TipsFlash.TH_FAILED);
            }
        } else if (!aLove.getStatus()) {
            log.info("用户 {}再次点赞|点踩 {}",userId,apiRootId);
            aLove.setStatus(true);
            aLove.setUpdateTime(new Date());
            int i = loveMapper.updateById(aLove);
            if (i == 1) {
                return ResultVO.success("成功啦！");
            } else {
                throw new TipsException(TipsFlash.TH_FAILED);
            }

        }
        throw new TipsException(TipsFlash.TH_FAILED);
    }

    @Override
    public List<Love> selectByApiRootId(String apiRootId) {
        return loveMapper.selectByApiRootId(apiRootId);
    }

    @Override
    public Boolean isLoveExist(String apiRootId, String userId) {
      Love love = selectByApiRootIdAndUserIdAndType(apiRootId, userId, (byte) 3);
        if (love==null) {
            return true;
        }
        return false;
    }

    @Override
    public Love selectByApiRootIdAndUserIdAndType(String apiRootId, String userId,Byte type) {
        QueryWrapper<Love> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("api_root_id", apiRootId).eq("user_id", userId)
                .eq("type",type);
        Love love = loveMapper.selectOne(queryWrapper);
        return love;
    }

    @Override
    public List<Love> selectThumbCountByRootIdAndType(String rootId, Byte type) {
        QueryWrapper<Love> wrapper = new QueryWrapper<>();
        wrapper.eq("api_root_id", rootId).eq("type", type)
        .eq("status",true).eq("love_type",Tips.POST_THUMB.getMsg());
        return loveMapper.selectList(wrapper);
    }

    @Override
    public List<Love> selectHateCountByRootIdAndType(String rootId, Byte type) {
        QueryWrapper<Love> wrapper = new QueryWrapper<>();
        wrapper.eq("api_root_id", rootId).eq("type", type)
                .eq("status",true).eq("love_type",Tips.POST_HATE.getMsg());
        return loveMapper.selectList(wrapper);
    }

    @Override
    public ResultVO changeLoveType(String apiRootId, String userId, Byte type) {
        Love love = selectByApiRootIdAndUserIdAndType(apiRootId, userId, type);
        String loveType = love.getLoveType();
        if (Tips.POST_THUMB.getMsg().equals(loveType)) {
            love.setLoveType(Tips.POST_HATE.getMsg());
            int i = loveMapper.updateById(love);
            if (i < 1) {
                throw new TipsException(TipsFlash.TH_FAILED);
            }
            return ResultVO.success("成功啦");
        }else if (Tips.POST_HATE.getMsg().equals(loveType)){
            love.setLoveType(Tips.POST_THUMB.getMsg());
            int i = loveMapper.updateById(love);
            if (i < 1) {
                throw new TipsException(TipsFlash.TH_FAILED);
            }
            return ResultVO.success("成功啦");

        }
        throw new TipsException(TipsFlash.TH_FAILED);
    }


//    public int addLove(String userId,String loveId) {
//        Love love = loveMapper.selectByLoveId(loveId);
//        if (love == null) {
//            Love love1 = new Love();
//            love1.setId(UuidUtil.getUuid());
//            love1.setLoveId(loveId);
//            love1.setType((byte) Tips.ARTICLE_THUMB.getCode().intValue());
//            love1.setStatus(true);
//            love1.setCreateTime(new Date());
//            love1.setUpdateTime(new Date());
//            love1.setEnabled(true);
//            love1.setFromId(UuidUtil.getUuid());
//            loveMapper.insert(love1);
//            int thumbNum = loveFromUserService.createThumb(userId, love1.getFromId());
//            if (thumbNum != 1) {
//                throw new TipsException(TipsFlash.ARTICLE_TH_FAILED);
//            }
//        } else {
//            int thumbNum = loveFromUserService.createThumb(userId, love.getFromId());
//            if (thumbNum != 1) {
//                throw new TipsException(TipsFlash.ARTICLE_TH_FAILED);
//            }
//        }
//        return 10;
//
//    }
//
//    @Override
//    public Love selectByLoveId(String loveId) {
//        return loveMapper.selectByLoveId(loveId);
//    }
}
