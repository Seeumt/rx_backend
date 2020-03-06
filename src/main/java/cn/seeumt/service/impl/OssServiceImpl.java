package cn.seeumt.service.impl;
import cn.seeumt.dao.CommentMapper;
import cn.seeumt.dataobject.Comment;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.utils.KeyUtil;
import cn.seeumt.vo.ResultVO;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.seeumt.config.AliyunOssConfig;
import cn.seeumt.dataobject.Oss;
import cn.seeumt.dao.OssMapper;
import cn.seeumt.dto.ImgDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.model.Img;
import cn.seeumt.service.OssService;
import cn.seeumt.utils.AliyunOssUtil;
import cn.seeumt.utils.UuidUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-08
 */
@Service
public class OssServiceImpl extends ServiceImpl<OssMapper, Oss> implements OssService {

    @Autowired
    private OssService ossService;
    @Autowired
    private OssMapper ossMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private AliyunOssConfig aliyunOssConfig;
    @Override
    public String saveOss(String originUrl,String parentId) {
        String dbUrl = AliyunOssUtil.getDbUrl(originUrl);
        Oss oss = new Oss();
        oss.setOssId(UuidUtil.getUuid());
        oss.setUrl(dbUrl);
        oss.setType(Tips.IMAGE.getCode());
        Date date = new Date();
        oss.setCreateTime(date);
        oss.setUpdateTime(date);
        oss.setEnabled(true);
        oss.setDeleted(false);
        oss.setParentId(parentId);
        ossService.save(oss);
        return AliyunOssUtil.cutSuffix(originUrl);
    }

    @Override
    public void saveOssForComment(String originUrl, String parentId) {
        saveOss(originUrl, parentId);
        Comment comment = commentMapper.selectById(parentId);
        comment.setCommentPic(originUrl);
        int i = commentMapper.updateById(comment);
        if (i < 1) {
            throw new TipsException(TipsFlash.INSERT_COMMENT_PIC_FAILED);
        }
    }

    @Override
    public ImgDTO queryByParentId(String parentId) {
        QueryWrapper<Oss> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<Oss> osses = ossMapper.selectList(wrapper);
        // TODO: 2020/1/31 List->Array
        List<String> urls = new ArrayList<>();
        List<Img> imgs = osses.stream().map(oss -> {
            Img img = new Img();
            BeanUtils.copyProperties(oss, img);
            img.setUrl(aliyunOssConfig.getUrlPrefix() + oss.getUrl());
            urls.add(aliyunOssConfig.getUrlPrefix() + oss.getUrl());
            return img;
        }).collect(Collectors.toList());
        ImgDTO imgDTO = new ImgDTO();
        imgDTO.setParentId(parentId);
        imgDTO.setImgs(imgs);
        String[] urlss=urls.toArray(new String[urls.size()]);
        imgDTO.setUrls(urlss);

        return imgDTO;
    }

    @Override
    public ResultVO deleteByOssId(String ossId) {
        int i = ossMapper.deleteById(ossId);
        if (i < 1) {
            throw new TipsException(TipsFlash.DELETE_OSS_FAILED);
        }
        return ResultVO.success("跟你说了再见");
    }

    @Override
    public String saveOssForMedia(String originUrl, String parentId, Integer type) {
        if (type.equals(Tips.POST.getCode())) {
            insertOss(originUrl,parentId);
            Comment comment = commentMapper.selectById(parentId);
            comment.setCommentPic(originUrl);
            commentMapper.updateById(comment);
        } else if (type.equals(Tips.ARTICLE.getCode())) {
            insertOss(originUrl,parentId);
        }
        return originUrl;
    }


    public void insertOss(String originUrl,String parentId) {
        Oss oss = new Oss();
        oss.setOssId(KeyUtil.genUniqueKey().toString());
        oss.setUrl(AliyunOssUtil.getDbUrl(originUrl));
        oss.setType(0);
        oss.setCreateTime(new Date());
        oss.setUpdateTime(new Date());
        oss.setEnabled(false);
        oss.setDeleted(false);
        oss.setParentId(parentId);
        int insert = ossMapper.insert(oss);
        if (insert < 1) {
            throw new TipsException(TipsFlash.INSERT_COMMENT_PIC_FAILED);
        }
    }
}
