package cn.seeumt.utils;


import cn.seeumt.dao.PostCommentMapper;
import cn.seeumt.dataobject.Love;
import cn.seeumt.dataobject.UserInfo;
import cn.seeumt.model.PostComment;
import cn.seeumt.model.Thumber;
import cn.seeumt.service.LoveService;
import cn.seeumt.service.PostCommentService;
import cn.seeumt.service.UserInfoService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//用List构建带有层次结构的json数据
//List父子节点构造树形Json
@Component
@Data
public class TreeUtil {
    @Autowired
    public PostCommentService postCommentService;
    @Autowired
    private LoveService loveService;
    @Autowired
    private UserInfoService userInfoService;


    private static TreeUtil treeUtil;

    @PostConstruct
    public void init() {
        treeUtil = this;
        treeUtil.postCommentService = this.postCommentService;
        treeUtil.loveService = this.loveService;
        treeUtil.userInfoService = this.userInfoService;
    }

    //将list集合转成树形结构的list集合
    // TODO: 2019/12/10 在这里要不要把postId当作根节点？
    public static List<PostComment> listToTree(List<PostComment> list,String postId) {
        //用递归找子。
        List<PostComment> treeList = new ArrayList<>();
        for (PostComment tree : list) {
            if (tree.getReplyId().equals(postId)) {
                //这个地方注意报空指针======
                treeList.add(findChildren(tree));
            }
        }
        return treeList;
    }

    //寻找子节点
    private static PostComment findChildren(PostComment tree) {
        List<PostComment> postComments = TreeUtil.treeUtil.postCommentService.selectByReplyId(tree.getId());
        List<Love> loves = TreeUtil.treeUtil.loveService.selectByApiRootId(tree.getId());
        if (loves == null) {
            tree.setThumbers(null);
        }
        Set set = new HashSet();
        for (Love love : loves) {
            set.add(love.getUserId());
        }
        List<Thumber> thumbers = new ArrayList<>();
        for (int i = 0; i < set.toArray().length; i++) {
            Thumber thumber = new Thumber();
            UserInfo userInfo = TreeUtil.treeUtil.userInfoService.selectByPrimaryKey(set.toArray()[i].toString());
            BeanUtils.copyProperties(userInfo, thumber);
            thumbers.add(thumber);
        }
        tree.setThumbers(thumbers);
        for (PostComment node : postComments) {
            if (node.getReplyId().equals(tree.getId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<>());
                }
                tree.getChildren().add(findChildren(node));
            }
        }
        return tree;
    }


}
