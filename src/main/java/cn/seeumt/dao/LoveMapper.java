package cn.seeumt.dao;

import cn.seeumt.dataobject.Love;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface LoveMapper extends BaseMapper<Love> {

    int deleteByPrimaryKey(String loveId);

    int insert(Love record);

    int insertSelective(Love record);

    Love selectByPrimaryKey(String loveId);

    int updateByPrimaryKeySelective(Love record);

    int updateByPrimaryKey(Love record);

    List<Love> selectByApiRootId(String apiRootId);
}
