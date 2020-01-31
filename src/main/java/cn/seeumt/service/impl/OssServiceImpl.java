package cn.seeumt.service.impl;
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
    private AliyunOssConfig aliyunOssConfig;
    @Override
    public String saveOss(String originUrl,String parentId) {
        String dbUrl = AliyunOssUtil.getDBUrl(originUrl);
        Oss oss = new Oss();
        oss.setOssId(UuidUtil.getUUID());
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
}
