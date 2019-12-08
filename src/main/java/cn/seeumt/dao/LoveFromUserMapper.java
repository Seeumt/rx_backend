package cn.seeumt.dao;

import cn.seeumt.dataobject.LoveFromUser;

import java.util.List;

public interface LoveFromUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(LoveFromUser record);

    int insertSelective(LoveFromUser record);

    LoveFromUser selectByPrimaryKey(String id);

    List<LoveFromUser> selectListByFromId(String fromId);


    int updateByPrimaryKeySelective(LoveFromUser record);

    int updateByPrimaryKey(LoveFromUser record);
}
