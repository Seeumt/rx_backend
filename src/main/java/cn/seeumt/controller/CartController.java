package cn.seeumt.controller;


import cn.seeumt.dataobject.Article;
import cn.seeumt.service.CartService;
import cn.seeumt.vo.CartVO;
import cn.seeumt.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Seeumt
 * @since 2020-01-27
 */
@RestController
@RequestMapping("/carts")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    @ResponseBody
    public ResultVO list(@PathVariable String userId){
        return cartService.list(userId);
    }

    @PostMapping("/{souvenirId}")
    public ResultVO add(@PathVariable("souvenirId") Integer souvenirId, String userId,Integer count){
        log.info("【添加到购物车】用户:user_id={} 添加商品:souvenir_id={} {}件到购物车",userId,souvenirId,count);
        return cartService.add(userId,souvenirId,count);
    }
//
//
//
    @PutMapping("/{souvenirId}")
    public ResultVO update(String userId,@PathVariable Integer souvenirId, Integer count ){
        return cartService.update(userId,souvenirId,count);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResultVO delete(String userId,String souvenirIds){

        return cartService.delete(userId,souvenirIds);
    }

    @GetMapping("/isSelect")
    @ResponseBody
    public ResultVO isSelectAll(String userId,
                                @RequestParam(value = "souvenirId",required = false) Integer souvenirId,
                                Boolean isChecked){
            return cartService.selectOrUnSelect(userId,souvenirId,isChecked);
    }


    @GetMapping("get_cart_souvenir_count")
    @ResponseBody
    public ResultVO getCartSouvenirCount(String userId){
        return cartService.getCartSouvenirCount(userId);
    }






}
