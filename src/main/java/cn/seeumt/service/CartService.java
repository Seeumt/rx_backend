package cn.seeumt.service;

import cn.seeumt.dataobject.Cart;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Seeumt
 * @since 2020-01-27
 */
public interface CartService{
    ResultVO add(String userId, Integer souvenirId, Integer count);
    ResultVO update(String userId,Integer souvenirId,Integer count);
    ResultVO delete(String userId,String souvenirIds);
    ResultVO list(String userId);
    ResultVO selectOrUnSelect (String userId,Integer souvenirId,Boolean isChecked);
    ResultVO getCartSouvenirCount(String userId);
}
