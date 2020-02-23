package cn.seeumt.controller;

import cn.seeumt.vo.ResultVO;
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
 * @author Seeumt
 */
@RestController
@RequestMapping("/uploads")
public class FileUploadController {
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
