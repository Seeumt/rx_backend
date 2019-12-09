package cn.seeumt.service;

import cn.seeumt.dataobject.LoveFromUser;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 15:14
 */
public interface LoveFromUserService {
    List<LoveFromUser> selectListByFromId(String fromId);

    int createThumb(String userId, String fromId);
}
