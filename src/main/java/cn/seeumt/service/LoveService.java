package cn.seeumt.service;

import cn.seeumt.dataobject.Love;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 12:25
 */
public interface LoveService {
    int addLove(String apiRootId,String userId);

    List<Love> selectByApiRootId(String apiRootId);
}
