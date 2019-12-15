package cn.seeumt.dao;

import cn.seeumt.dataobject.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}
