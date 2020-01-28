package cn.seeumt.controller;


import cn.seeumt.dataobject.Article;
import cn.seeumt.vo.ResultVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-27
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    /**
     *
     * @param userId
     * @param souvenirId
     * @return
     */
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO add(String userId,String souvenirId,Integer count) {
        return null;
    }
}
