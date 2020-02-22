package cn.seeumt.service.impl;
import java.util.Date;

import cn.seeumt.dao.CommentMapper;
import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.Comment;
import cn.seeumt.dataobject.User;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.model.CommentContent;
import cn.seeumt.service.CommentService;
import cn.seeumt.utils.TreeUtil;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Seeumt
 * @date 2019/12/8 16:17
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public CommentFirstMo getLuckyCommentsAndChildren(String apiRootId) {

        List<Comment> allLuckyComments = getAllLuckyComments(apiRootId);
        //找到所有0级评论
        List<CommentMo> commentMos = assemblecCommentMOList(allLuckyComments);
        Integer n = 0;
        for (Comment allLuckyComment : allLuckyComments) {
            n=n+selectCommentCountByRootIdAndType(allLuckyComment.getCommentId(), (byte) 3).size();
        }
        return new CommentFirstMo(commentMos,n);

    }

    @Override
    public Integer getAllCommentCount(String apiRootId) {
        List<Comment> allLuckyComments = getAllLuckyComments(apiRootId);
        //找到所有0级评论
        List<CommentMo> commentMos = assemblecCommentMOList(allLuckyComments);
        Integer n = 0;
        for (Comment allLuckyComment : allLuckyComments) {
            n=n+selectCommentCountByRootIdAndType(allLuckyComment.getCommentId(), (byte) 3).size();
        }
        return n;
    }

    public List<CommentMo> assemblecCommentMOList(List<Comment> comments) {
        return     comments.stream().map(comment -> {
                    CommentMo commentMO = new CommentMo();
                    BeanUtils.copyProperties(comment, commentMO);
                    List<Comment> comment1s = selectCommentCountByRootIdAndType(comment.getCommentId(), (byte) 3);
                    List<Comment> commentLess = selectCommentLessByRootIdAndType(comment.getCommentId(), (byte) 3);
                    commentMO.setCommentVos(assembleCommentVOList(commentLess));
                    commentMO.setChildrenCount(assembleCommentVOList(comment1s).size());
                    User user = userMapper.selectById(comment.getUserId());
                    if (user == null) {
                        throw new TipsException(TipsFlash.QUERY_USER_FAILED);
                    }
                    commentMO.setUsername(user.getUsername());
                    commentMO.setFaceIcon(user.getFaceIcon());
                    return commentMO;
                }).collect(Collectors.toList());
 }

    @Override
    public List<CommentVO> getLuckyChildData(String apiRootId) {
        List<Comment> comments = selectCommentCountByRootIdAndType(apiRootId, (byte) 3);
        return assembleCommentVOList(comments);
    }

    public List<CommentVO> assembleCommentVOList(List<Comment> comment1s) {

        return comment1s.stream().map(comment1 -> {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment1, commentVO);
            User user = userMapper.selectById(comment1.getUserId());
            if (user == null) {
                throw new TipsException(TipsFlash.QUERY_USER_FAILED);
            }
            commentVO.setUsername(user.getUsername());
            commentVO.setFaceIcon(user.getFaceIcon());
            //查询父级评论信息获取其userId
            Comment commentT = commentMapper.selectById(comment1.getParentId());
            if (commentT == null) {
                throw new TipsException(TipsFlash.QUERY_TARGET_COMMENT_FAILED);
            }
            //获取userId后进行查询该用户信息

            User userT = userMapper.selectById(commentT.getUserId());
            if (userT == null) {
                throw new TipsException(TipsFlash.QUERY_USER_FAILED);
            }
            commentVO.setTargetUserId(userT.getUserId());
            commentVO.setTargetUsername(userT.getUsername());
            return commentVO;
        }).collect(Collectors.toList());
    }


    public static Comment createComment(String apiRootId, String userId, String content,Byte type,String parentId) {
        Comment comment = new Comment();
        comment.setCommentId(UuidUtil.getUuid());
        comment.setType(type);
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setEnabled(true);
        comment.setApiRootId(apiRootId);
        comment.setParentId(parentId);
        return comment;
    }

    @Override
    public List<cn.seeumt.model.Comment> findNextLevelCommentsByParentId(String parentId) {
        List<Comment> commentsSomeLevel = commentMapper.findNextLevelCommentsByParentId(parentId);
        return assembleCommentModel(commentsSomeLevel);
    }

    public List<cn.seeumt.model.Comment> assembleCommentModel(List<Comment> commentsSomeLevel) {
        List<cn.seeumt.model.Comment> comments = new ArrayList<>();
        for (Comment comment : commentsSomeLevel) {
            cn.seeumt.model.Comment commentModel = new cn.seeumt.model.Comment();
            BeanUtils.copyProperties(comment,commentModel);
            comments.add(commentModel);
        }
        return comments;
    }

    @Override
    public List<CommentVO> findAllLevelCommentsByApiRootId(String apiRootId) {
        List<Comment> comments0 = selectCommentCountByParentIdAndType(apiRootId, (byte) 3);
        List<cn.seeumt.model.Comment> comments = assembleCommentModel(comments0);
        List<CommentVO> commentVOS = comments.stream().map(comment -> {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            User user = userMapper.selectById(comment.getUserId());
            if (user == null) {
                throw new TipsException(TipsFlash.QUERY_USER_FAILED);
            }
            commentVO.setUsername(user.getUsername());
            commentVO.setFaceIcon(user.getFaceIcon());
            if (!commentVO.getParentId().equals(commentVO.getApiRootId())) {
            //查询父级评论信息获取其userId
            Comment commentT = commentMapper.selectById(comment.getParentId());
            if (commentT == null) {
                throw new TipsException(TipsFlash.QUERY_TARGET_COMMENT_FAILED);
            }
            //获取userId后进行查询该用户信息

                User userT = userMapper.selectById(commentT.getUserId());
                if (userT == null) {
                    throw new TipsException(TipsFlash.QUERY_USER_FAILED);
                }
                commentVO.setTargetUserId(commentT.getUserId());
                commentVO.setTargetUsername(userT.getUsername());
            }
            commentVO.setChildrenCount(selectCommentCountByRootIdAndType(comment.getCommentId(), (byte) 3).size());

            return commentVO;
        }).collect(Collectors.toList());
        return commentVOS;
    }




    @Override
    public List<Comment> selectCommentCountByRootIdAndType(String rootId, Byte type) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("api_root_id", rootId).eq("type", type);
        return commentMapper.selectList(wrapper);
    }

    public List<Comment> selectCommentLessByRootIdAndType(String rootId, Byte type) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("api_root_id", rootId).eq("type", type).last("limit 3");
        return commentMapper.selectList(wrapper);
    }

    @Override
    public List<Comment> selectCommentCountByParentIdAndType(String parentId, Byte type) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId).eq("type", type);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public List<CommentFirstVO> queryHomeComments(String apiRootId) {
        //找出所有的一级评论
        List<Comment> comments = queryOneLevelComments(apiRootId);
        List<CommentFirstVO> commentFirstVOList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentFirstVO commentFirstVO = new CommentFirstVO();
            BeanUtils.copyProperties(comment, commentFirstVO);
            User user = userMapper.selectById(comment.getUserId());
            if (user == null) {
                throw new TipsException(TipsFlash.QUERY_USER_FAILED);
            }
            commentFirstVO.setUserId(user.getUserId());
            commentFirstVO.setUsername(user.getUsername());
            commentFirstVO.setFaceIcon(user.getFaceIcon());
            // TODO: 2020/2/18
            commentFirstVO.setCommentVos1(findAllLevelCommentsByApiRootId(commentFirstVO.getCommentId()));
            commentFirstVO.setCommentCount(findAllLevelCommentsByApiRootId(commentFirstVO.getCommentId()).size());
            commentFirstVOList.add(commentFirstVO);
        }
        return commentFirstVOList;
    }

    @Override
    public List<Comment> queryOneLevelComments(String apiRootId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("api_root_id", apiRootId).eq("parent_id", apiRootId);
        return commentMapper.selectList(wrapper);
    }


    // TODO: 2020/2/18 New
    @Override
    public List<CommentFirstVO> queryHomeAndAllComments(String apiRootId) {
        //找出所有的一级评论
        List<Comment> comments = queryOneLevelComments(apiRootId);
        List<CommentFirstVO> commentFirstVOList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentFirstVO commentFirstVO = new CommentFirstVO();
            BeanUtils.copyProperties(comment, commentFirstVO);
            User user = userMapper.selectById(comment.getUserId());
            if (user == null) {
                throw new TipsException(TipsFlash.QUERY_USER_FAILED);
            }
            commentFirstVO.setUserId(user.getUserId());
            commentFirstVO.setUsername(user.getUsername());
            commentFirstVO.setFaceIcon(user.getFaceIcon());
            List<cn.seeumt.model.Comment> comments1 = findNextLevelCommentsByParentId(commentFirstVO.getCommentId());
            // TODO: 2020/2/18 这里要改一下 TreeUtil
            List<cn.seeumt.model.Comment> comments2 = TreeUtil.listToTreeNew(comments1, commentFirstVO.getCommentId());
            commentFirstVO.setCommentVos(comments2);
            commentFirstVO.setCommentCount(findAllLevelCommentsByApiRootId(commentFirstVO.getCommentId()).size());
            commentFirstVOList.add(commentFirstVO);
        }
        return commentFirstVOList;
    }



    @Override
    public int commentForRoot(String apiRootId, String userId, String content,Byte type) {
        Comment comment = CommentServiceImpl.createComment(apiRootId, userId, content, type, apiRootId);
        return commentMapper.insert(comment);
    }

    @Override
    public ResultVO comment(String apiRootId, String userId, String content, Byte type, String parentId) {
        Comment comment = CommentServiceImpl.createComment(apiRootId, userId, content, type, parentId);
        int i = commentMapper.insert(comment);
        if (i < 1) {
            throw new TipsException(TipsFlash.INSERT_COMMENT_FAILED);
        }
        return ResultVO.success(comment.getCommentId());
    }



    @Override
    public List<Comment> getAllLuckyComments(String apiRootId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("api_root_id", apiRootId).eq("parent_id", 0);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public ResultVO listByParentId(String parentId) {
        List<cn.seeumt.model.Comment> comments = findNextLevelCommentsByParentId(parentId);
        List<CommentVO> commentVOS = comments.stream().map(comment -> {
            CommentVO commentVO = new CommentVO();
            User user = userMapper.selectById(comment.getUserId());
            if (user == null) {
                throw new TipsException(TipsFlash.QUERY_USER_FAILED);
            }
            commentVO.setUsername(user.getUsername());
            commentVO.setFaceIcon(user.getFaceIcon());
            commentVO.setChildrenCount(selectCommentCountByRootIdAndType(comment.getCommentId(), (byte) 3).size());
            BeanUtils.copyProperties(comment, commentVO);
            return commentVO;
        }).collect(Collectors.toList());
        return ResultVO.success(commentVOS);
    }



    @Override
    public Comment selectByCommentId(String commentId) {
        return null;
    }

    @Override
    public List<CommentContent> findUserCommentsOfAnArticle(String articleId, String userId) {
        return null;
    }

    @Override
    public List<CommentContent> findUserCommentsOfAnComments(String userId, String commentId) {
        return null;
    }


}
