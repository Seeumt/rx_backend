package cn.seeumt.dao;

import cn.seeumt.dataobject.Love;
import org.apache.ibatis.annotations.Param;

public interface LoveMapper{
    int deleteByPrimaryKey(String id);

    int insert(Love record);

    int insertSelective(Love record);

    Love selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Love record);

    int updateByPrimaryKey(Love record);

    Love selectByLoveIdAndType(@Param("loveId") String loveId,@Param("type") Integer type);
}
