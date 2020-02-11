package cn.seeumt.service.impl;
import java.util.*;

import cn.seeumt.dao.PostMapper;
import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.Follow;
import cn.seeumt.dataobject.Post;
import cn.seeumt.dataobject.User;
import cn.seeumt.dto.ImgDTO;
import cn.seeumt.dto.PostDTO;
import cn.seeumt.dto.PostListDataItem;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.*;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.LoveVO;
import cn.seeumt.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OssService ossService;
    @Autowired
    private FollowService followService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LoveService loveService;


    @Override
    public List<PostListDataItem> listFollowAndRecommendData(String userId) {

//        PageHelper.startPage(1, 3);
        List<Post> posts = postMapper.selectList(null);
//        PageInfo<Post> pageInfo = new PageInfo<>(posts);
        PostListDataItem postListDataItem0 = null;
        PostListDataItem postListDataItem1 = null;
        List<PostListDataItem> postListDataItemList = new ArrayList<>();
        Set<PostDTO> followSet = new HashSet<>();
        Set<PostDTO> notFollowSet = new HashSet<>();
        Set<PostDTO> recommendSet = new HashSet<>();
        if (!"".equals(userId)) {
            List<Follow> allLiker = followService.getAllLiker(userId);
            Boolean isIdol = false;
             for (Post post : posts) {
                List<Boolean> trues = new ArrayList<>();
                List<Boolean> falses = new ArrayList<>();
                PostDTO postDTO = assemblePostDTO(post);
                  for (Follow liker : allLiker) {
                    if (liker.getFollowerId().equals(post.getUserId())) {
                        trues.add(!isIdol);
                    }else {
                        falses.add(isIdol);
                    }
                }
                if (trues.size() > 0) {
                    postDTO.setIsFollow(true);
                    followSet.add(postDTO);
                }else {
                    postDTO.setIsFollow(false);
                    notFollowSet.add(postDTO);
                }

            }
        }else {
            for (Post post : posts) {
                PostDTO postDTO = assemblePostDTO(post);
                postDTO.setIsFollow(false);
                notFollowSet.add(postDTO);
            }
        }

        recommendSet.addAll(followSet);
        recommendSet.addAll(notFollowSet);
        postListDataItem0 = new PostListDataItem(0,followSet);
        postListDataItem1 = new PostListDataItem(1, recommendSet);

        postListDataItemList.add(postListDataItem0);
        postListDataItemList.add(postListDataItem1);
        return postListDataItemList;
    }

    @Override
    public PostListDataItem listFollowList(String userId) {
        PostListDataItem postListDataItem0 = new PostListDataItem(0,null);
        if (!"".equals(userId)) {
            Set<PostDTO> followSet = getFollowSet(userId);
            postListDataItem0.setId(0);
            postListDataItem0.setPosts(followSet);
        }
        return postListDataItem0;
    }

    @Override
    public PostListDataItem listRecommendList(String userId) {

        Set<PostDTO> recommendSet = new HashSet<>();
        Set<PostDTO> postDTOSetNotFollow = new HashSet<>();
        List<Post> posts = postMapper.selectList(null);
        if (!"".equals(userId)) {
            List<Follow> allLiker = followService.getAllLiker(userId);
            for (Post post : posts) {
                PostDTO postDTO = assemblePostDTO(post);
              for (Follow liker : allLiker) {
                    if (!liker.getFollowerId().equals(post.getUserId())) {
                        postDTO.setIsFollow(false);
                        postDTOSetNotFollow.add(postDTO);
                    }else {

                    }
                }
            }
            Set<PostDTO> followSet = getFollowSet(userId);
            recommendSet.addAll(postDTOSetNotFollow);
            recommendSet.addAll(followSet);
        }else {
            for (Post post : posts) {
                PostDTO postDTO = assemblePostDTO(post);
                        postDTO.setIsFollow(false);
                        recommendSet.add(postDTO);
            }
        }
        PostListDataItem postListDataItem1 = new PostListDataItem(1,recommendSet);

        return postListDataItem1;
    }

    private Set<PostDTO> getFollowSet(String userId) {
        Set<PostDTO> followSet = new HashSet<>();
        List<Follow> allLiker = followService.getAllLiker(userId);
        List<Post> posts = postMapper.selectList(null);
       loop: for (Post post : posts) {
            PostDTO postDTO = assemblePostDTO(post);
            for (Follow liker : allLiker) {
                if (liker.getFollowerId().equals(post.getUserId())) {
                    postDTO.setIsFollow(true);
                    followSet.add(postDTO);
                    continue loop;
                }
            }
        }
        return followSet;
    }

    @Override
    public PostDTO selectByPostId(String postId) {
        Post post = postMapper.selectByPrimaryKey(postId);
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post,postDTO);
        User user = userMapper.selectById(post.getUserId());
        UserVO userVO= new UserVO();
        BeanUtils.copyProperties(user, userVO);
        ImgDTO imgDTO = ossService.queryByParentId(post.getUserId());
        String[] urls = imgDTO.getUrls();
        List<String> imgUrls = new ArrayList<>(Arrays.asList(urls));
        postDTO.setImgUrls(urls);
        postDTO.setUserVO(userVO);
        return postDTO;
    }

    @Override
    public int sendPost() {
        Post post = new Post();
        post.setPostId(UuidUtil.getUUID());
        post.setType(true);
        post.setImgId(UuidUtil.getUUID());
        post.setUserId("Seeumt");
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());
        post.setDeleted(false);
        return postMapper.insert(post);
    }

    @Override
    public Post selectByUserId(String userId) {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return postMapper.selectOne(wrapper);

    }

    public PostDTO assemblePostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        User user = userMapper.selectById(post.getUserId());
        if (user == null) {
            throw new TipsException(456, "此动态用户不存在");
        }
        postDTO.setFaceIcon(user.getFaceIcon());
        postDTO.setUsername(user.getUsername());
        ImgDTO imgDTO = ossService.queryByParentId(post.getPostId());
        if (imgDTO == null) {
            postDTO.setImgUrls(null);
        }else {
            String[] urls = imgDTO.getUrls();
            if ( urls.length==0) {
                postDTO.setImgUrls(null);
            }else {
                postDTO.setImgUrls(urls);
            }

        }
        postDTO.setImgUrls(imgDTO.getUrls());
        LoveVO loveVO = new LoveVO();
        loveVO.setType("");
        loveVO.setLikeCount(loveService.selectThumbCountByRootIdAndType(post.getPostId(), (byte) 3).size());
        loveVO.setHateCount(commentService.selectCommentCountByRootIdAndType(post.getPostId(), (byte) 3).size());
        postDTO.setLove(loveVO);
        return postDTO;
    }

}
