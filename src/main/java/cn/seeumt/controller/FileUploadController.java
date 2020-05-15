package cn.seeumt.controller;

import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传接口
 * @author Seeumt
 */
@Api(tags = {"上传文件"})
@RestController
@RequestMapping("/uploads")
public class FileUploadController {
    /**
     * 文件上传接口
     * @param files 文件数组
     * @return ResultVO
     */
    @PostMapping("/imgs")
    public ResultVO fileUpload(@RequestPart("imgs") MultipartFile[] files) {

        for(MultipartFile file : files){
            String filePath = "E:\\" + file.getOriginalFilename();
            try {
                file.transferTo(new File(filePath));
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        return ResultVO.success("上传成功！");
    }
}
