package cn.seeumt.service.impl;
import java.util.Date;
import java.util.*;

import cn.seeumt.dao.PostMapper;
import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.Follow;
import cn.seeumt.dataobject.Love;
import cn.seeumt.dataobject.Post;
import cn.seeumt.dataobject.User;
import cn.seeumt.dto.ImgDTO;
import cn.seeumt.dto.PostDTO;
import cn.seeumt.dto.PostListDataItem;
import cn.seeumt.enums.Tips;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.*;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.LoveVO;
import cn.seeumt.vo.ResultVO;
import cn.seeumt.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private TagService tagServie;
    @Autowired
    private MediaTagsService mediaTagsService;


    @Override
    public ResultVO get(String postId) {
        Post post = postMapper.selectById(postId);
        PostDTO postDTO = assemblePostDTO(post,null);
        return ResultVO.success(postDTO);
    }

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
            List<Follow> allIdol = followService.getAllIdol(userId);
            Boolean isIdol = false;
             for (Post post : posts) {
                List<Boolean> trues = new ArrayList<>();
                List<Boolean> falses = new ArrayList<>();
                PostDTO postDTO = assemblePostDTO(post,userId);
                  for (Follow idol : allIdol) {
                    if (idol.getIdolId().equals(post.getUserId())) {
                        trues.add(!isIdol);
                    }else {
                        falses.add(isIdol);
                    }
                }
                if (trues.size() > 0||post.getUserId().equals(userId)) {
                    postDTO.setIsFollow(true);
                    followSet.add(postDTO);
                }else {
                    postDTO.setIsFollow(false);
                    notFollowSet.add(postDTO);
                }

            }
        }else {
            for (Post post : posts) {
                PostDTO postDTO = assemblePostDTO(post,userId);
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
    public ResultVO listFollowList(String userId) {
        Set<PostDTO> followSet = new HashSet<>();
        PostListDataItem postListDataItem0 = new PostListDataItem(0,followSet);
        if (!"".equals(userId)) {
            followSet = getFollowSet(userId);
            postListDataItem0.setPosts(followSet);
        }
        return ResultVO.success(postListDataItem0);
    }

    @Override
    public ResultVO listNotFollowList(String userId) {
        PageHelper.startPage(1, 1);
        List<Post> posts = postMapper.selectList(null);
        Set<PostDTO> followSet = new HashSet<>();
        Set<PostDTO> notFollowSet = new HashSet<>();
        Set<PostDTO> recommendSet = new HashSet<>();
        if (!"".equals(userId)) {
            List<Follow> allIdol = followService.getAllIdol(userId);
            Boolean isIdol = false;
            for (Post post : posts) {
                List<Boolean> trues = new ArrayList<>();
                List<Boolean> falses = new ArrayList<>();
                PostDTO postDTO = assemblePostDTO(post,userId);
                for (Follow idol : allIdol) {
                    if (idol.getIdolId().equals(post.getUserId())) {
                        trues.add(!isIdol);
                    }else {
                        falses.add(isIdol);
                    }
                }
                if (trues.size() > 0||post.getUserId().equals(userId)) {
                    postDTO.setIsFollow(true);
                    followSet.add(postDTO);
                }else {
                    postDTO.setIsFollow(false);
                    notFollowSet.add(postDTO);
                }

            }
        }else {
            for (Post post : posts) {
                PostDTO postDTO = assemblePostDTO(post,userId);
                postDTO.setIsFollow(false);
                notFollowSet.add(postDTO);
            }
        }
        recommendSet.addAll(followSet);
        recommendSet.addAll(notFollowSet);
        List<PostDTO> recommendPostDTOS = new ArrayList<>(recommendSet);
        PageInfo<PostDTO> pageInfo = new PageInfo<>(recommendPostDTOS);
        PostListDataItem postListDataItem1 = new PostListDataItem(1,pageInfo);
        return ResultVO.success(postListDataItem1);
    }

    @Override
    public PostListDataItem listRecommendList(String userId) {

        Set<PostDTO> recommendSet = new HashSet<>();
        Set<PostDTO> postDTOSetNotFollow = new HashSet<>();
        List<Post> posts = postMapper.selectList(null);
        if (!"".equals(userId)) {
            List<Follow> allIdol = followService.getAllIdol(userId);
            for (Post post : posts) {
                PostDTO postDTO = assemblePostDTO(post,userId);
              for (Follow idol : allIdol) {
                    if (!idol.getIdolId().equals(post.getUserId())) {
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
                PostDTO postDTO = assemblePostDTO(post,userId);
                        postDTO.setIsFollow(false);
                        recommendSet.add(postDTO);
            }
        }
        PostListDataItem postListDataItem1 = new PostListDataItem(1,recommendSet);

        return postListDataItem1;
    }
    private Set<PostDTO> getFollowSet(String userId) {
        Set<PostDTO> followSet = new HashSet<>();
        List<Follow> allIdol = followService.getAllIdol(userId);
        List<Post> posts = postMapper.selectList(null);
         for (Post post : posts) {
            PostDTO postDTO = assemblePostDTO(post,userId);
            for (Follow idol : allIdol) {
                if (idol.getIdolId().equals(post.getUserId())||post.getUserId().equals(userId)) {
                    postDTO.setIsFollow(true);
                    followSet.add(postDTO);
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
    @Transactional(rollbackFor = TipsException.class)
    public ResultVO send(cn.seeumt.form.Post formPost) {
        String tagIds = formPost.getTagIds();
        String postId = UuidUtil.getUUID();
        List<String> tagIdList = Splitter.on(",").splitToList(tagIds);
        if (CollectionUtils.isEmpty(tagIdList)) {
            insertPost(formPost, postId);
        } else {
            insertPost(formPost,postId);
            mediaTagsService.insert(tagIdList,postId);
        }
        return ResultVO.success(postId);
    }

    public void insertPost(cn.seeumt.form.Post formPost,String postId) {
        Post post = new Post();
        post.setPostId(postId);
        post.setType(formPost.getType());
        post.setContent(formPost.getContent());
        post.setImgId("66");
        post.setUserId(formPost.getUserId());
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());
        post.setDeleted(false);
        int insert = postMapper.insert(post);
        if (insert < 1) {
            throw new TipsException(TipsFlash.INSERT_POST_FAILED);
        }

    }

    @Override
    public Post selectByUserId(String userId) {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return postMapper.selectOne(wrapper);

    }

    @Override
    public ResultVO delete(String postId) {
        int i = postMapper.deleteById(postId);
        if (i < 1) {
           throw new TipsException(TipsFlash.DELETED_FAILED);
        }
        return ResultVO.success(Tips.DELETED_SUCCESS);
    }

    public PostDTO assemblePostDTO(Post post,String userId) {
        LoveVO loveVO = new LoveVO();
        if (userId != null) {
            Love love = loveService.selectByApiRootIdAndUserIdAndType(post.getPostId(), userId, (byte) 3);
            if (love == null) {
                loveVO.setType("");
            }else {
                loveVO.setType(love.getLoveType());
            }
        }
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

        loveVO.setLikeCount(loveService.selectThumbCountByRootIdAndType(post.getPostId(), (byte) 3).size());
        loveVO.setHateCount(loveService.selectHateCountByRootIdAndType(post.getPostId(), (byte) 3).size());
        postDTO.setLove(loveVO);
        postDTO.setCommentCount(commentService.selectCommentCountByRootIdAndType(post.getPostId(), (byte) 3).size());
        List<String> tagsIds = mediaTagsService.findTagIdsByParentId(post.getPostId());
        if (tagsIds.size() == 0) {
            postDTO.setTags(null);
        }else {
            postDTO.setTags(tagServie.findTagVOByTagIds(tagsIds));
        }
        return postDTO;
    }

}
