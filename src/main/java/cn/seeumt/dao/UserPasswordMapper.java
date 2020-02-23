package cn.seeumt.dao;

import cn.seeumt.dataobject.UserPassword;

/**
 * @author Seeumt
 */
public interface UserPasswordMapper {
    /**
     * 通过主键删除
     * @param id id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /***
     * 插入记录
     * @param record
     * @return int
     */
    int insert(UserPassword record);

    /**
     * 选择性插入
     * @param record
     * @return int
     */
    int insertSelective(UserPassword record);

    /**
     * 通过主键选择
     * @param id
     * @return
     */
    UserPassword selectByPrimaryKey(String id);

    /**
     * 通过主键选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserPassword record);

    /**
     * 通过主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserPassword record);

    /**
     * t通过用户id选择
     * @param userId
     * @return
     */
    UserPassword selectByUserId(String userId);

}
