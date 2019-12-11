package cn.seeumt.service.impl;
import java.util.Date;

import cn.seeumt.dao.PostMapper;
import cn.seeumt.dataobject.Post;
import cn.seeumt.dto.PostDTO;
import cn.seeumt.service.PostService;
import cn.seeumt.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;


    @Override
    public PostDTO selectByPostId(String postId) {
        Post post = postMapper.selectByPrimaryKey(postId);
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post,postDTO);
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

}
