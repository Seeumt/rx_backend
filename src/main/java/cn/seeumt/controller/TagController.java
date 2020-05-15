package cn.seeumt.controller;


import cn.seeumt.dto.ImgDTO;
import cn.seeumt.service.TagService;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 标签
 * @author Seeumt
 * @since 2019-12-21
 */
@Api(tags = {"标签"})
@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 请求到所有的tag
     * @return ResultVO
     */
    @GetMapping("/")
    public ResultVO get() {
        System.out.println("this is online");
        return tagService.get();
    }
}
