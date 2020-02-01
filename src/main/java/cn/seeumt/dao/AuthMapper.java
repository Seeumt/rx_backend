package cn.seeumt.dao;


import cn.seeumt.dataobject.Role;
import cn.seeumt.dataobject.User;
import cn.seeumt.model.UserDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
public interface AuthMapper extends BaseMapper<User> {
    /**
     * 根据用Id查找用户
     * @param userId
     * @return
     */
//    UserDetail findByUsername(@Param("userId") String userId);

    /**
     * 创建新用户
     * @param userDetail
     */
    void insert(UserDetail userDetail);

    /**
     * 创建用户角色
     * @param userId
     * @param roleId
     * @return
     */
//    int insertRole(@Param("userId") long userId, @Param("roleId") long roleId);

    /**
     * 根据角色id查找角色
     * @param roleId
     * @return
     */
//    Role findRoleById(@Param("roleId") long roleId);

    /**
     * 根据用户id查找该用户角色
     * @param userId
     * @return
     */
//    Role findRoleByUserId(@Param("userId") long userId);
}
