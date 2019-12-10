//package cn.seeumt.service.impl;
//
//import cn.seeumt.enums.Tips;
//import cn.seeumt.enums.TipsFlash;
//import cn.seeumt.exception.TipsException;
//import cn.seeumt.utils.UuidUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @author Seeumt
// * @date 2019/12/8 15:14
// */
//@Service
//public class LoveFromUserServiceImpl implements LoveFromUserService {
//    @Autowired
//    private LoveFromUserMapper loveFromUserMapper;
//    @Override
//    public List<LoveFromUser> selectListByFromId(String fromId) {
//        return loveFromUserMapper.selectListByFromId(fromId);
//    }
//
//    @Override
//    public int createThumb(String userId, String fromId) {
//        LoveFromUser loveFromUser = new LoveFromUser();
//        loveFromUser.setId(UuidUtil.getUUID());
//        loveFromUser.setType((byte) Tips.ARTICLE_THUMB.getCode().intValue());
//        loveFromUser.setStatus(true);
//        /*
//        点赞时暂时设置为null
//        */
//        loveFromUser.setContentId(null);
//        loveFromUser.setFromUserId(userId);
//        loveFromUser.setCreateTime(new Date());
//        loveFromUser.setUpdateTime(new Date());
//        loveFromUser.setFromId(fromId);
//        int thumbNum = loveFromUserMapper.insert(loveFromUser);
//        if (thumbNum != 1) {
//            throw new TipsException(TipsFlash.ARTICLE_THUMB_FAILED);
//        }
//        return thumbNum;
//    }
//
//}
