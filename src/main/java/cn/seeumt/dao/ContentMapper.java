package cn.seeumt.dao;

import cn.seeumt.dataobject.Content;

public interface ContentMapper {
    int insert(Content record);

    int insertSelective(Content record);
}