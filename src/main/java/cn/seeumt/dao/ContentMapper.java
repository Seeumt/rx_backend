package cn.seeumt.dao;

import cn.seeumt.dataobject.Content;

public interface ContentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(String id);

    Content selectByContentId(String contentId);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
}
