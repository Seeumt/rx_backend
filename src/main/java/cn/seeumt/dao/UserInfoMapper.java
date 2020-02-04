package cn.seeumt.dao;

import cn.seeumt.dataobject.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserInfoMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
