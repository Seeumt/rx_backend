package cn.seeumt.utils;


import cn.seeumt.dao.CommentMapper;
import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.User;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import cn.seeumt.service.LoveService;
import cn.seeumt.service.CommentService;
import cn.seeumt.vo.CommentVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

//用List构建带有层次结构的json数据
//List父子节点构造树形Json
@Component
@Data
public class TreeUtil {

    @Autowired
    private LoveService loveService;

    private static TreeUtil treeUtil;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;

    @PostConstruct
    public void init() {
        treeUtil = this;
        treeUtil.commentService = this.commentService;
        treeUtil.loveService = this.loveService;
        treeUtil.userMapper = this.userMapper;
        treeUtil.commentMapper = this.commentMapper;
    }

    //将list集合转成树形结构的list集合
    // TODO: 2019/12/10 在这里要不要把postId当作根节点？
    public static List<Comment> listToTree(List<Comment> list, String parentId) {
        //用递归找子。
        List<Comment> treeList = new ArrayList<>();
        for (Comment tree : list) {
            if (tree.getParentId().equals(parentId)) {
                //这个地方注意报空指针======
                tree.setExpand(true);
                treeList.add(findChildren(tree));
            }
        }
        return treeList;
    }

    //寻找子节点
    private static Comment findChildren(Comment tree) {
        List<Comment> comments = TreeUtil.treeUtil.commentService.findNextLevelCommentsByParentId(tree.getCommentId());
//        TreeUtil.treeUtil.commentService.findNextLevelCommentsByParentId(tree.getCommentId())
        List<Thumber> thumbers = ThumberUtil.allThumbers(tree.getCommentId());
        tree.setThumbers(thumbers);
        for (Comment node : comments) {
            if (node.getParentId().equals(tree.getCommentId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<>());
                }
                tree.getChildren().add(findChildren(node));
            }
        }
        return tree;
    }

    public static List<Comment> listToTreeNew(List<Comment> list, String parentId) {
        //用递归找子。
        List<Comment> treeList = new ArrayList<>();
        for (Comment tree : list) {
            if (tree.getParentId().equals(parentId)) {
                //这个地方注意报空指针======
                tree.setExpand(true);
                treeList.add(findChildrenNew(tree));
            }
        }
        return treeList;
    }

    //寻找子节点
    private static Comment findChildrenNew(Comment tree) {
        List<Comment> comments = TreeUtil.treeUtil.commentService.findNextLevelCommentsByParentId(tree.getCommentId());
//        TreeUtil.treeUtil.commentService.findNextLevelCommentsByParentId(tree.getCommentId())
        List<Thumber> thumbers = ThumberUtil.allThumbers(tree.getCommentId());
        tree.setThumbers(thumbers);
        for (Comment node : comments) {
//            如果查不到子列表，就给他设置成无（new Arraylist<>())
            if (node.getParentId().equals(tree.getCommentId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<>());
                }
                tree.getChildren().add(findChildrenNew(node));
            }
        }
        User user = TreeUtil.treeUtil.userMapper.selectById(tree.getUserId());
        if (user == null) {
            throw new TipsException(TipsFlash.QUERY_USER_FAILED);
        }
        tree.setUsername(user.getUsername());
        tree.setFaceIcon(user.getFaceIcon());
        if (!tree.getParentId().equals(tree.getApiRootId())) {
            //查询父级评论信息获取其userId
            cn.seeumt.dataobject.Comment commentT = TreeUtil.treeUtil.commentMapper.selectById(tree.getParentId());
            if (commentT == null) {
                throw new TipsException(TipsFlash.QUERY_TARGET_COMMENT_FAILED);
            }
            //获取userId后进行查询该用户信息

            User userT = TreeUtil.treeUtil.userMapper.selectById(commentT.getUserId());
            if (userT == null) {
                throw new TipsException(TipsFlash.QUERY_USER_FAILED);
            }
            tree.setTargetUserId(commentT.getUserId());
            tree.setTargetUsername(userT.getUsername());
        }
        tree.setChildrenCount(TreeUtil.treeUtil.commentService.selectCommentCountByRootIdAndType(tree.getCommentId(), (byte) 3).size());
        return tree;
    }


}
