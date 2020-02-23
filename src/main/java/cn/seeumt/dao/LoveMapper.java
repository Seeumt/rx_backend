package cn.seeumt.dao;

import cn.seeumt.dataobject.Love;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author Seeumt
 */
public interface LoveMapper extends BaseMapper<Love> {

    /**
     * 通过主键删除
     * @param loveId id
     * @return
     */
    int deleteByPrimaryKey(String loveId);

    @Override
    /***
     * 插入记录
     * @param record
     * @return int
     */
    int insert(Love record);

    /**
     * 通过通过主键选择
     * @param loveId
     * @return
     */
    Love selectByPrimaryKey(String loveId);


    /**
     * 查找List
     * @param apiRootId
     * @return
     */
    List<Love> selectByApiRootId(String apiRootId);
}
