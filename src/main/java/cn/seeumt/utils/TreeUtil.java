package cn.seeumt.utils;


import cn.seeumt.dao.PostCommentMapper;
import cn.seeumt.model.PostComment;
import cn.seeumt.service.PostCommentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//用List构建带有层次结构的json数据
//List父子节点构造树形Json
@Component
@Data
public class TreeUtil {
    @Autowired
    public PostCommentService postCommentService;

    private static TreeUtil treeUtil;

    @PostConstruct
    public void init() {
        treeUtil = this;
        treeUtil.postCommentService = this.postCommentService;
    }

    //将list集合转成树形结构的list集合
    // TODO: 2019/12/10 在这里要不要把postId当作根节点？
    public static List<PostComment> listToTree(List<PostComment> list) {
        //用递归找子。
        List<PostComment> treeList = new ArrayList<>();
        for (PostComment tree : list) {
            if (tree.getReplyId().equals("0")) {
                //这个地方注意报空指针======
                treeList.add(findChildren(tree, list));
            }
        }
        return treeList;
    }

    //寻找子节点
    private static PostComment findChildren(PostComment tree, List<PostComment> list) {
        List<PostComment> postComments = TreeUtil.treeUtil.postCommentService.selectByReplyId(tree.getId());

        for (PostComment node : postComments) {
            if (node.getReplyId().equals(tree.getId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<>());
                }
                tree.getChildren().add(findChildren(node, postComments));
            }
        }
        return tree;
    }


}
