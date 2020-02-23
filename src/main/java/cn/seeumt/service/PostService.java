package cn.seeumt.service;


import cn.seeumt.dataobject.Post;
import cn.seeumt.dto.PostDTO;
import cn.seeumt.dto.PostListDataItem;
import cn.seeumt.vo.PostVO;
import cn.seeumt.vo.ResultVO;

import java.util.List;

/**
 * @author Seeumt
 */
public interface PostService{

    /**
     * 根据动态id主键查询PostDTO
     * @param postId 动态id
     * @return
     */
    ResultVO getDto(String postId);

    /**
     * 查询推荐与关注列表
     * @param userId 用户id
     * @return List<PostListDataItem>
     */
    List<PostListDataItem> listFollowAndRecommendData(String userId);

    /**
     * 关注列表
     * @param userId
     * @return
     */
    ResultVO listFollowList(String userId);

    /**
     * 展示非关注列表
     * @param userId 用户id
     * @return ResultVO
     */
    ResultVO listNotFollowList(String userId);

    /**
     * 列出推荐列表
     * @param userId 用户id
     * @return PostListDataItem
     */
    PostListDataItem listRecommendList(String userId);

    /**
     * 通过用户发布的动态的Id得到一个完整的动态详情
     * @param postId 用户发布的动态的Id
     * @return 将查询到的Post 转化为 PostDTO
     */
    PostDTO selectByPostId(String postId);

    /**
     * 发布动态
     * @param post 用户表单实体类
     * @return int
     */
    ResultVO send(cn.seeumt.form.Post post);


    /**
     * 选择用户id(*)
     * @param userId 用户id
     * @return Post
     */
    Post selectByUserId(String userId);

    /**
     * 删除动态
     * @param postId 动态id
     * @return ResultVO
     */
    ResultVO delete(String postId);

    /**
     * 查询单一动态
     * @param postId 动态id
     * @return ResultVO
     */
    ResultVO get(String postId);

    /**
     * 更新内容
     * @param postId 动态id
     * @param content 内容
     * @return ResultVO
     */
    ResultVO updateContent(String postId, String content);

    /**
     * 通过关键字检索
     * @param keywords 关键字
     * @return List<PostVO>
     */
    List<PostVO> search(String keywords);
}


