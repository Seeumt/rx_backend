package cn.seeumt.utils;


import cn.seeumt.model.Comment;
import cn.seeumt.model.Thumber;
import cn.seeumt.service.LoveService;
import cn.seeumt.service.CommentService;
import lombok.Data;
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

    @PostConstruct
    public void init() {
        treeUtil = this;
        treeUtil.commentService = this.commentService;
        treeUtil.loveService = this.loveService;
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


}
