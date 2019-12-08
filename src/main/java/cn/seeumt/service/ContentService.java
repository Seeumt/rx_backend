package cn.seeumt.service;

import cn.seeumt.dataobject.Content;
import cn.seeumt.model.CommentContent;

import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 19:13
 */
public interface ContentService {
    Content selectByContentId(String contentId);
}
