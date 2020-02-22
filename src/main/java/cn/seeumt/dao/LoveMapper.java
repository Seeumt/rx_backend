package cn.seeumt.dao;

import cn.seeumt.dataobject.Love;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface LoveMapper extends BaseMapper<Love> {

    int deleteByPrimaryKey(String loveId);

    @Override
    int insert(Love record);


    Love selectByPrimaryKey(String loveId);



    List<Love> selectByApiRootId(String apiRootId);
}
