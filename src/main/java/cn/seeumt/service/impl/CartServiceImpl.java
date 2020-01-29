package cn.seeumt.service.impl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.seeumt.dao.SouvenirMapper;
import cn.seeumt.dataobject.Cart;
import cn.seeumt.dao.CartMapper;
import cn.seeumt.dataobject.Souvenir;
import cn.seeumt.dto.CartDTO;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.CartService;
import cn.seeumt.service.SouvenirService;
import cn.seeumt.utils.BigDecimalUtil;
import cn.seeumt.vo.CartVO;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seeumt
 * @since 2020-01-27
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private SouvenirMapper souvenirMapper;

    @Override
    public ResultVO add(String userId, Integer souvenirId, Integer count) {
        Cart cart = selectByUserIdAndSouvenirId(userId, souvenirId);
        if (cart == null) {
            Cart cartItem = new Cart();
            cartItem.setUserId(userId);
            cartItem.setSouvenirId(souvenirId);
            cartItem.setQuantity(count);
            cartItem.setChecked(true);
            cartItem.setCreateTime(new Date());
            cartItem.setUpdateTime(new Date());
            int insert = cartMapper.insert(cartItem);
            if (insert < 0) {
                throw new TipsException(TipsFlash.ADD_TO_CART_FAILED);
            }
        }else {
            count = cart.getQuantity() + count;
            cart.setQuantity(count);
            cartMapper.updateById(cart);
        }
        // TODO: 2020/1/28 list方法是干嘛的 转去了getCartVOList
        return this.list(userId);
    }

    @Override
    public ResultVO update(String userId, Integer souvenirId, Integer count) {
        if(souvenirId == null || count == null){
            return ResultVO.error(TipsFlash.INVAILD_ARGUMENT);
        }
        Cart cart = selectByUserIdAndSouvenirId(userId, souvenirId);
        if(cart != null){
            cart.setQuantity(count);
        }
        cartMapper.updateById(cart);
        return this.list(userId);
    }

    @Override
    public ResultVO delete(String userId, String souvenirIds) {
        List<String> souvenirIdList = Splitter.on(",").splitToList(souvenirIds);
        if(CollectionUtils.isEmpty(souvenirIdList)){
            return ResultVO.error(TipsFlash.INVAILD_ARGUMENT);
        }
        deleteByUserIdAndProductIds(userId,souvenirIdList);
        return this.list(userId);
    }

    /**
     *
     * @param userId
     * @param souvenirIdList
     */
    private void deleteByUserIdAndProductIds(String userId, List<String> souvenirIdList) {
//        for (String souvenirId : souvenirIdList) {
//            QueryWrapper<Cart> wrapper = new QueryWrapper<>();
//            wrapper.eq("user_id", userId).eq("souvenir_id", souvenirId);
//            cartMapper.delete(wrapper);
//        }

        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).in("souvenir_id", souvenirIdList);
        cartMapper.delete(wrapper);
    }

    /**
     *
     * @param userId 根据用户名
     * @return CartVO
     */
    @Override
    public ResultVO list(String userId) {
        CartVO cartVO = this.getCartVOLimit(userId);
        return ResultVO.success(cartVO);
    }

    @Override
    public ResultVO selectOrUnSelect(String userId,Integer souvenirId,Boolean isChecked) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq(souvenirId != null, "souvenir_id", souvenirId)
                .eq("user_id",userId);
        List<Cart> carts = cartMapper.selectList(wrapper);
        for (Cart cart : carts) {
            cart.setChecked(isChecked);
            cartMapper.updateById(cart);
        }
        return this.list(userId);
    }

    @Override
    public ResultVO getCartSouvenirCount(String userId) {
        List<Cart> carts = selectByUserId(userId);
        int souvenirCount = 0;
        if (CollectionUtils.isNotEmpty(carts)) {
            for (Cart cart : carts) {
                souvenirCount = souvenirCount + cart.getQuantity();
            }
        }
        return ResultVO.success(souvenirCount);
    }


    private CartVO getCartVOLimit(String userId){
        CartVO cartVO = new CartVO();
        // TODO: 2020/1/28 查出来这个用户的所有购物车item干嘛呢？---要实时更新库存！！！
        List<Cart> cartList = selectByUserId(userId);
        List<CartDTO> cartDTOList = Lists.newArrayList();
        BigDecimal cartTotalPrice = new BigDecimal("0");
        if(CollectionUtils.isNotEmpty(cartList)){
            for(Cart cartItem : cartList){
                CartDTO cartDTO = new CartDTO();
                BeanUtils.copyProperties(cartItem,cartDTO);
                Souvenir souvenir = souvenirMapper.selectById(cartItem.getSouvenirId());
                if(souvenir != null){
                    BeanUtils.copyProperties(souvenir, cartDTO);
                    // TODO: 2020/1/28  buyLimitCount干嘛的呢
                    //判断库存
                    int buyLimitCount = 0;
                    if(souvenir.getStock() >= cartItem.getQuantity()){
                        //库存充足的时候
                        buyLimitCount = cartItem.getQuantity();
                        cartDTO.setLimitQuantity("限制数量成功！");
                    }else{
                        buyLimitCount = souvenir.getStock();
                        cartDTO.setLimitQuantity("限制数量失败！");
                        // TODO: 2020/1/28 为什么要这么麻烦地更新呢？
                        //购物车中更新有效库存
//                        Cart cartForQuantity = new Cart();
//                        cartForQuantity.set(cartItem.getId());
//                        cartForQuantity.setQuantity(buyLimitCount);
                        cartItem.setQuantity(buyLimitCount);
                        cartMapper.updateById(cartItem);
                    }
                    cartDTO.setQuantity(buyLimitCount);
                    //计算总价
                    cartDTO.setCartItemTotalPrice(BigDecimalUtil.mul(souvenir.getPrice().doubleValue(),cartItem.getQuantity()));
                }
                if(cartItem.getChecked()){
                    //如果已经勾选,增加到整个的购物车总价中
                    cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(),cartDTO.getCartItemTotalPrice().doubleValue());
                }
                cartDTOList.add(cartDTO);
            }
        }
        cartVO.setCartTotalPrice(cartTotalPrice);
        cartVO.setCartDTOList(cartDTOList);
        cartVO.setAllChecked(this.getAllCheckedStatus(userId));

        return cartVO;
    }

    private boolean getAllCheckedStatus(String userId){
        if(userId == null){
            return false;
        }
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("checked", 0);
        List<Cart> carts = cartMapper.selectList(wrapper);
        return carts.size() == 0;

    }


    /**
     * 根据用户名找cartItems
     * @param userId 用户名
     * @return Cart
     */
    @Override
    public List<Cart> selectByUserId(String userId) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return cartMapper.selectList(wrapper);
    }

    /**
     * 根据用户名找cartItems
     * @param userId 用户名
     * @return Cart
     */
    @Override
    public Cart selectByUserIdAndSouvenirId(String userId,Integer souvenirId) {
        QueryWrapper<Cart> addWrapper = new QueryWrapper<>();
        addWrapper.eq("user_id", userId).eq("souvenir_id", souvenirId);
        return cartMapper.selectOne(addWrapper);
    }
    /**
     * 根据用户名找cartItems
     * @param userId 用户名
     * @return Cart
     */
    @Override
    public int deleteByUserIdAndSouvenirId(String userId,Integer souvenirId) {
        QueryWrapper<Cart> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("user_id", userId).eq("souvenir_id", souvenirId);
        return cartMapper.delete(deleteWrapper);
    }


}
