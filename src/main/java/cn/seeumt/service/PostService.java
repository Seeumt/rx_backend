package cn.seeumt.service;


import cn.seeumt.dataobject.Post;
import cn.seeumt.dto.PostDTO;
import cn.seeumt.dto.PostListDataItem;

import java.util.List;

public interface PostService{

    List<PostListDataItem> listFollowAndRecommendData(String userId);

    PostListDataItem listFollowList(String userId);

    PostListDataItem listRecommendList(String userId);

    /**
     * 通过用户发布的动态的Id得到一个完整的动态详情
     * @param postId 用户发布的动态的Id
     * @return 将查询到的Post 转化为 PostDTO
     */

    PostDTO selectByPostId(String postId);

    /**
     * 发布动态
     * @return int
     */
    int sendPost();


    Post selectByUserId(String userId);

}


