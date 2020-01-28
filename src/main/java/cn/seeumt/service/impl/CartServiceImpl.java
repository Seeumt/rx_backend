package cn.seeumt.service.impl;

import cn.seeumt.dataobject.Cart;
import cn.seeumt.dao.CartMapper;
import cn.seeumt.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-27
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}
