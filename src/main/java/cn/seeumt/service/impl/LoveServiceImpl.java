package cn.seeumt.service.impl;

import cn.seeumt.dao.LoveMapper;
import cn.seeumt.dataobject.Love;
import cn.seeumt.service.LoveService;
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
     * @param loveId 文章设置好的
     * @return
     */

    @Autowired
    private LoveMapper loveMapper;
    @Override
    public int addLove(String loveId) {
        Love love = new Love();
        love.setLoveId(loveId);
        love.setType((byte) 1);
        love.setStatus(true);
        love.setCreateTime(new Date());
        love.setUpdateTime(new Date());
        love.setEnabled(true);
        return loveMapper.insert(love);
    }
}
