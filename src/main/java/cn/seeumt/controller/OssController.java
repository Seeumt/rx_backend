package cn.seeumt.controller;


import cn.seeumt.dto.ImgDTO;
import cn.seeumt.model.Img;
import cn.seeumt.service.OssService;
import cn.seeumt.utils.AliyunOssUtil;
import cn.seeumt.vo.ResultVO;
import com.aliyun.oss.OSSException;
import com.aliyuncs.exceptions.ClientException;
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
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/insert")
    @ResponseBody
    public ResultVO fileUpload(@RequestPart("oss") MultipartFile[] files, String parentId) throws IOException {
        List<String> urlList = new ArrayList<>();
        for (MultipartFile file : files) {
            String originUrl = AliyunOssUtil.getOriginUrl(file);
            String urls = ossService.saveOss(originUrl, parentId);
            urlList.add(urls);
        }
        return ResultVO.success(urlList);
    }

    @ResponseBody
    @PostMapping("/imgs/{parentId}")
    public ResultVO getPicture(@PathVariable("parentId") String parentId) {
        ImgDTO imgDTO = ossService.queryByParentId(parentId);
        return ResultVO.success(imgDTO);
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
