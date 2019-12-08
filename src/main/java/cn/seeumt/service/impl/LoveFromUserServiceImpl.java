package cn.seeumt.service.impl;

import cn.seeumt.dao.LoveFromUserMapper;
import cn.seeumt.dataobject.LoveFromUser;
import cn.seeumt.service.LoveFromUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 15:14
 */
@Service
public class LoveFromUserServiceImpl implements LoveFromUserService {
    @Autowired
    private LoveFromUserMapper loveFromUserMapper;
    @Override
    public List<LoveFromUser> selectListByFromId(String fromId) {
        return loveFromUserMapper.selectListByFromId(fromId);
    }

}
