package cn.seeumt.controller;


import cn.seeumt.dto.ImgDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.model.Img;
import cn.seeumt.service.OssService;
import cn.seeumt.utils.AliyunOssUtil;
import cn.seeumt.utils.WechatUtil;
import cn.seeumt.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSException;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-08
 */
@RestController
@RequestMapping("/oss")
@Slf4j
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/insert")
    public ResultVO fileUpload(@RequestPart("file") MultipartFile[] files, String parentId) throws IOException, HttpException {
        List<String> urlList = new ArrayList<>();
        for (MultipartFile file : files) {
            String urls = "";
            JSONObject jsonObject = WechatUtil.checkImg(file);
            String errcode = jsonObject.getString("errcode");
            String originUrl = AliyunOssUtil.getOriginUrl(file);
            if (errcode.equals(Tips.RISK_CONTENT.getCode().toString())) {
                 urls = ossService.saveExtraOss("e3f4aa0b811c4ccabcb684a2e1e2290f.jpg", parentId);
            }else{
                 urls = ossService.saveOss(originUrl, parentId);
            }
            urlList.add(urls);
        }
        return ResultVO.success(urlList);
    }

    @PostMapping("/{type}")
    public ResultVO saveOssForMedia(@RequestPart("file") MultipartFile file, String parentId,
    @PathVariable("type") Integer type) throws IOException, HttpException {
        if ("".equals(parentId)) {
            return ResultVO.error(TipsFlash.INVAILD_ARGUMENT);
        }
        String originUrl = AliyunOssUtil.getOriginUrl(file);
        log.info("进行图片评论,图片地址：{}",originUrl);
        ossService.saveOssForMedia(originUrl, parentId,type);
        JSONObject jsonObject = WechatUtil.checkImg(file);
        String errcode = jsonObject.getString("errcode");
        if (errcode.equals(Tips.RISK_CONTENT.getCode().toString())) {
            return ResultVO.success("https://seeumt.oss-cn-hangzhou.aliyuncs.com/e3f4aa0b811c4ccabcb684a2e1e2290f.jpg");
        }
        return ResultVO.success(AliyunOssUtil.cutSuffix(originUrl));
    }

    @PostMapping("/article")
    public ResultVO article(@RequestPart("file") MultipartFile file, String parentId) throws IOException {
        String originUrl = AliyunOssUtil.getOriginUrl(file);
        log.info("进行图片评论,图片地址：{}",originUrl);
        ossService.saveOssForComment(originUrl, parentId);
        return ResultVO.success(originUrl);
    }

    @GetMapping("/imgs/{parentId}")
    public ResultVO getPicture(@PathVariable("parentId") String parentId) {
        ImgDTO imgDTO = ossService.queryByParentId(parentId);
        return ResultVO.success(imgDTO);
    }









    @DeleteMapping("/imgs/{ossId}")
    public ResultVO delPicture(@PathVariable("ossId") String ossId) {
        return  ossService.deleteByOssId(ossId);

    }

}


//    /**
//     * 改
//     * @param TPicture
//     */
//    @ResponseBody
//    @PutMapping("/update")
//    public void updatePicture(TPicture TPicture) {
//        pictureService.updatePicture(TPicture);
//    }
//
//    /**
//     * 删
//     * @param id
//     */
//    @ResponseBody
//    @DeleteMapping("/delete/{id}")
//    public void deletePicture(@PathVariable("id") Integer id) {
//        pictureService.deletePicture(id);
//    }
//


//
//    /**
//     * 把文件保存到阿里云OSS，返回路径保存到数据库
//     * @param fileupload
//     * @return
//     * @throws OSSException
//     * @throws ClientException
//     * @throws IOException
//     */
//}
