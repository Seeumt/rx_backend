package cn.seeumt.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import cn.seeumt.dao.PostMapper;
import cn.seeumt.dataobject.Post;
import cn.seeumt.dto.ImgDTO;
import cn.seeumt.dto.PostDTO;
import cn.seeumt.service.OssService;
import cn.seeumt.service.PostService;
import cn.seeumt.service.UserInfoService;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private OssService ossService;


    @Override
    public PostDTO selectByPostId(String postId) {
        Post post = postMapper.selectByPrimaryKey(postId);
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post,postDTO);
        UserVO userVO = userInfoService.selectByUserId(post.getUserId());
        ImgDTO imgDTO = ossService.queryByParentId(post.getUserId());
        String[] urls = imgDTO.getUrls();
        ArrayList<String> imgUrls = new ArrayList<>(Arrays.asList(urls));
        postDTO.setImgUrls(imgUrls);
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

}
