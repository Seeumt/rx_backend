package cn.seeumt.service;

import cn.seeumt.dataobject.Love;

/**
 * @author Seeumt
 * @date 2019/12/8 12:25
 */
public interface LoveService {
    int addLove(String userId,String loveId);

    Love selectByLoveId(String loveId);
}
