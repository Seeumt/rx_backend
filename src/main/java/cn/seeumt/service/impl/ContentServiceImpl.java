package cn.seeumt.service.impl;

import cn.seeumt.dao.ArticleMapper;
import cn.seeumt.dao.CommentFromUserMapper;
import cn.seeumt.dao.CommentMapper;
import cn.seeumt.dao.ContentMapper;
import cn.seeumt.dataobject.Article;
import cn.seeumt.dataobject.CommentFromUser;
import cn.seeumt.dataobject.Content;
import cn.seeumt.model.CommentContent;
import cn.seeumt.service.ArticleService;
import cn.seeumt.service.ContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seeumt
 * @date 2019/12/8 19:14
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private CommentFromUserMapper commentFromUserMapper;

    @Override
    public Content selectByContentId(String contentId) {
        return contentMapper.selectByContentId(contentId);
    }


}
