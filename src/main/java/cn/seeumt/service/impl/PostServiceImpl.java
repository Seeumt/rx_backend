package cn.seeumt.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.seeumt.dao.PostCommentMapper;
import cn.seeumt.dao.PostMapper;
import cn.seeumt.dataobject.Post;
import cn.seeumt.dataobject.PostComment;
import cn.seeumt.dto.PostCommentDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.service.PostService;
import cn.seeumt.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostCommentMapper postCommentMapper;

    List<PostComment> postComments1 = new ArrayList<>();

    @Override
    public List<cn.seeumt.model.PostComment> findAllCommentsOfAPost(String postId, String replyId) {

        List<PostComment> commentsLevelOne = postCommentMapper.findAllCommentsOfAPostByPostIdAndReplyId(postId, replyId);
        List<cn.seeumt.model.PostComment> postComments = new ArrayList<>();
        for (PostComment postComment : commentsLevelOne) {
            cn.seeumt.model.PostComment postComment1 = new cn.seeumt.model.PostComment();
            BeanUtils.copyProperties(postComment,postComment1);
            postComments.add(postComment1);
        }
        return postComments;
    }
//    @Override
//    public PostCommentDTO findAllCommentsOfAPost1(String postId, String replyId) {
//        PostCommentDTO postCommentDTO = new PostCommentDTO();
//        List<PostComment> list = findAllCommentsOfAPost(postId, replyId);
//        if (list.size() > 0) {
//            for (int i = 0; i < list.size(); i++) {
//                this.postComments1.add(list.get(i));
//                //将查询结果添加到set集合中
//                String replyIdNext = list.get(i).getId();
//                //获取到该结果中的commentid字段，作为条件来查询它的下一层数据
//                findAllCommentsOfAPost(postId, replyIdNext);
//                //递归
//            }
//        }
//        postCommentDTO.setPostComment(postComments1);
//        return postCommentDTO;
//
//    }


            /**
             * Set<Comment> thisSet = new HashSet<Comment>();//在方法外声明一个set集合，用来存放查询结果
             * 	public void findReoly(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
             * 		thisSet.clear();//在请求进来的时候讲set集合清空
             * 		int commentid = Integer.parseInt(request.getParameter("commentid"));
             * 		Set<Comment> sets = findReolyOther(commentid);//调用递归方法
             * 		JsonConfig jsonConfig = new JsonConfig();
             * 		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
             * 		JSONArray array = JSONArray.fromObject(sets, jsonConfig);
             * 		JSONObject json = new JSONObject();
             * 		json.put(0, array);
             * 		response.getWriter().write(json.toString());//返回json格式的数据
             *        }
             * 	public Set<Comment> findReolyOther(int commentid){
             * 		//调用Dao的查询方法，若list.size()>0，也就是还有下一层数据，就进入循环
             * 		List<Comment> list = userSer.findReoly(commentid);
             * 		if(list.size()>0){
             * 			for(int i=0;i<list.size();i++){
             * 				thisSet.add(list.get(i));//将查询结果添加到set集合中
             * 				int id = list.get(i).getCommentid();//获取到该结果中的commentid字段，作为条件来查询它的下一层数据
             * 				findReolyOther(id);//递归
             *            }
             *        }
             * 		return thisSet;//返回结果集
             *    }
             * ————————————————
             * 版权声明：本文为CSDN博主「Christmas-z」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
             * 原文链接：https://blog.csdn.net/weixin_44538399/article/details/93164146
             */



            @Override
    public int comment(String postId) {
        PostComment postComment = new PostComment();
        postComment.setId(UuidUtil.getUUID());
        postComment.setType((byte) Tips.POST_COMMENT.getCode().intValue());
        postComment.setCreateTime(new Date());
        postComment.setUpdateTime(new Date());
        postComment.setEnabled(false);
        postComment.setReplyId("0");
        postComment.setPostId(postId);
        postComment.setUserId("RRRR");
        return postCommentMapper.insert(postComment);
    }


    @Override
    public int sendPost() {
        Post post = new Post();
        post.setPostId(UuidUtil.getUUID());
        post.setType(true);
        post.setContentId(UuidUtil.getUUID());
        post.setImgId(UuidUtil.getUUID());
        post.setLoveId(UuidUtil.getUUID());
        post.setUserId("Seeumt");
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());
        post.setDeleted(false);
        return postMapper.insert(post);
    }
}
