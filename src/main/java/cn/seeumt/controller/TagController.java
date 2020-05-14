package cn.seeumt.controller;


import cn.seeumt.dto.ImgDTO;
import cn.seeumt.service.TagService;
import cn.seeumt.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author Seeumt
 * @since 2019-12-21
 */
@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public ResultVO get() {
        System.out.println("this is online");
        return tagService.get();
    }
}
