package cn.seeumt.controller;

import cn.seeumt.dto.OrderDTO;
import cn.seeumt.service.OrderService;
import cn.seeumt.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
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
//@Api(tags = "默认是xxx-controller --> order",description = "默认是 Xxx Controller，--> 订单描述 (已过时)")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/createFromCart")
    @ResponseBody
    public ResultVO create(String userId, Integer shippingId){


        return orderService.createOrder(userId,shippingId);
    }

    @GetMapping("/find")
    public OrderDTO findOne(Long orderId) {
        return orderService.findOne(orderId);
    }

    @PostMapping("/list")
    public ResultVO list(@RequestParam(value = "userId")String userId,
                         @RequestParam(value = "num",required = false,defaultValue = "1") int num,
                         @RequestParam(value = "size", required = false, defaultValue = "3")int size) {

        PageInfo<OrderDTO> orderDTOPageInfo = orderService.list(num, size, userId);
        return ResultVO.success(orderDTOPageInfo);
    }



}
