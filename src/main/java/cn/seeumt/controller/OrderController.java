package cn.seeumt.controller;

import cn.seeumt.dto.OrderDTO;
import cn.seeumt.service.OrderService;
import cn.seeumt.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/29 11:53
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/createFromCart")
    @ResponseBody
    public ResultVO create(String userId, Integer shippingId){
        log.info("用户 {} 创建订单 订单号:{}",userId,shippingId);
        return orderService.createOrder(userId,shippingId);
    }

    @PostMapping("/buyNow")
    @ResponseBody
    public ResultVO buyNow(String userId, Integer shippingId, Integer souvenirId, Integer count) {
        return orderService.createOrderNow(userId, shippingId, souvenirId, count);
    }

    @GetMapping("/find")
    public OrderDTO findOne(Long orderId) {
        return orderService.findOne(orderId);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestParam(value = "userId")String userId,
                         @RequestParam(value = "num",required = false,defaultValue = "1") int num,
                         @RequestParam(value = "size", required = false, defaultValue = "3")int size) {

        PageInfo<OrderDTO> orderDtoPageInfo = orderService.list(num, size, userId);
        return ResultVO.success(orderDtoPageInfo);
    }



}
