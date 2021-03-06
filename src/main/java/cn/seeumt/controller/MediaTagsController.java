package cn.seeumt.controller;


import cn.seeumt.dto.ImgDTO;
import cn.seeumt.service.MediaTagsService;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Media-Tag
 * @author Seeumt
 * @since 2019-12-21
 */
@Api(tags = {"Media-Tag"})
@RestController
@RequestMapping("/mt")
public class MediaTagsController {
    @Autowired
    private MediaTagsService mediaTagsService;
    @GetMapping("/{parentId}")
    public ResultVO getPicture(@PathVariable("parentId") String parentId) {
        List<String> tagsId = mediaTagsService.findTagIdsByParentId(parentId);
        return ResultVO.success(tagsId);
    }
}
