package cn.seeumt.dao;

import cn.seeumt.dataobject.UserPassword;

public interface UserPasswordMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserPassword record);

    int insertSelective(UserPassword record);

    UserPassword selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserPassword record);

    int updateByPrimaryKey(UserPassword record);


    UserPassword selectByUserId(String userId);

}
