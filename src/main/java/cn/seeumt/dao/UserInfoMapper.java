package cn.seeumt.dao;

import cn.seeumt.dataobject.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author Seeumt
 */
public interface UserInfoMapper extends BaseMapper<User> {

    /**
     * 插入记录
     * @param record
     * @return
     */
    @Override
    int insert(User record);

    /**
     * 通过主键选择
     * @param id
     * @return
     */
    User selectByPrimaryKey(String id);


}
