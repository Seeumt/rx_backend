package cn.seeumt.utils;


import cn.seeumt.dao.CategoryMapper;
import cn.seeumt.dao.CommentMapper;
import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.User;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import cn.seeumt.service.CategoryService;
import cn.seeumt.service.LoveService;
import cn.seeumt.service.CommentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author Seeumt
 */
@Component
@Data
public class TreeUtil {

    @Autowired
    private LoveService loveService;

    private static TreeUtil treeUtil;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @PostConstruct
    public void init() {
        treeUtil = this;
        treeUtil.commentService = this.commentService;
        treeUtil.loveService = this.loveService;
        treeUtil.categoryService = this.categoryService;
        treeUtil.userMapper = this.userMapper;
        treeUtil.commentMapper = this.commentMapper;
        treeUtil.categoryMapper = this.categoryMapper;
    }

    //将list集合转成树形结构的list集合
    /**
     * 找到最近一级的List
     * @param list
     * @param parentId
     * @return
     */
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

    /**
     * 寻找子节点
     * @param tree
     * @return
     */
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

    /**
     * 寻找子节点新
     * @param tree
     * @return
     */
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

    /**
     * 找到最近一级的List
     * @param list
     * @param parentId
     * @return
     */
    public static List<cn.seeumt.model.Category> listToCategoryTree(List<cn.seeumt.model.Category> list, Integer parentId) {
        //用递归找子。
        List<cn.seeumt.model.Category> treeList = new ArrayList<>();
        for (cn.seeumt.model.Category tree : list) {
            if (tree.getParentId().equals(parentId)) {
                //这个地方注意报空指针======
                treeList.add(findCategoryChildren(tree));
            }
        }
        return treeList;
    }

    /**
     * 寻找子节点
     * @param tree
     * @return
     */
    private static cn.seeumt.model.Category findCategoryChildren(cn.seeumt.model.Category tree) {
        List<cn.seeumt.model.Category> categories = TreeUtil.treeUtil.categoryService.getNextLevelCategory(tree.getCategoryId());
        for (cn.seeumt.model.Category node : categories) {
            if (node.getParentId().equals(tree.getCategoryId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<>());
                }
                tree.getChildren().add(findCategoryChildren(node));
            }
        }
        return tree;
    }


}
