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


    /**
     * 展示关注列表
     * @param userId 用户id
     * @param currentNum 当前页
     * @param size 每页条数
     * @return ResultVO
     */
    ResultVO getIdolsPosts(String userId, int currentNum, int size);

    /**
     * 展示推荐列表
     * @param userId 用户id
     * @param currentNum 当前页
     * @param size 每页条数
     * @return ResultVO
     */
    ResultVO getRecommendPosts(String userId, int currentNum, int size);



}


