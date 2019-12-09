package cn.seeumt.service.impl;

import cn.seeumt.dao.LoveMapper;
import cn.seeumt.dataobject.Love;
import cn.seeumt.enums.Tips;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.LoveFromUserService;
import cn.seeumt.service.LoveService;
import cn.seeumt.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Seeumt
 * @date 2019/12/8 12:28
 */
@Service
public class LoveServiceImpl implements LoveService {
    /**
     * @param loveId
     * @return
     */

    @Autowired
    private LoveMapper loveMapper;
    @Autowired
    private LoveFromUserService loveFromUserService;

    @Override
    public int addLove(String userId,String loveId) {
        Love love = loveMapper.selectByLoveId(loveId);
        if (love == null) {
            Love love1 = new Love();
            love1.setId(UuidUtil.getUUID());
            love1.setLoveId(loveId);
            love1.setType((byte) Tips.ARTICLE_THUMB.getCode().intValue());
            love1.setStatus(true);
            love1.setCreateTime(new Date());
            love1.setUpdateTime(new Date());
            love1.setEnabled(true);
            love1.setFromId(UuidUtil.getUUID());
            loveMapper.insert(love1);
            int thumbNum = loveFromUserService.createThumb(userId, love1.getFromId());
            if (thumbNum != 1) {
                throw new TipsException(TipsFlash.ARTICLE_THUMB_FAILED);
            }
        } else {
            int thumbNum = loveFromUserService.createThumb(userId, love.getFromId());
            if (thumbNum != 1) {
                throw new TipsException(TipsFlash.ARTICLE_THUMB_FAILED);
            }
        }
        return 10;

    }

    @Override
    public Love selectByLoveId(String loveId) {
        return loveMapper.selectByLoveId(loveId);
    }
}
