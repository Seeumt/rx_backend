package cn.seeumt.service;

import cn.seeumt.dataobject.Cart;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Seeumt
 * @since 2020-01-27
 */
public interface CartService{
    /**
     * 添加商品到购物车
     * @param userId 用户id
     * @param souvenirId 纪念品id
     * @param count 个数
     * @return ResultVO
     */
    ResultVO add(String userId, Integer souvenirId, Integer count);

    /**
     *更新用户单一纪念品购物车信息
     * @param userId 用户id
     * @param souvenirId 纪念品id
     * @param count 个数
     * @return ResultVO
     */
    ResultVO update(String userId,Integer souvenirId,Integer count);

    /**
     * 删除纪念品
     * @param userId 用户id
     * @param souvenirIds 纪念品id集合
     * @return ResultVO
     */
    ResultVO delete(String userId,String souvenirIds);

    /**
     * 根据用户id查找购物车
     * @param userId
     * @return ResultVO
     */
    ResultVO list(String userId);

    /**
     * 全选或者反选
     *
     * @param userId     用户id
     * @param souvenirId 纪念品id
     * @param isChecked  全选/反选
     * @return ResultVO
     */
    ResultVO selectOrUnSelect(String userId, Integer souvenirId, Boolean isChecked);

    /**
     * 获取购物车纪念品数量
     * @param userId 用户id
     * @return ResultVO
     */
    ResultVO getCartSouvenirCount(String userId);

    /**
     * 根据用户id查找购物车
     * @param userId userId
     * @return List<Cart>
     */
    List<Cart> selectByUserId(String userId);

    /**
     * 查询单一纪念品购物车信息
     *
     * @param userId 用户id
     * @param souvenirId 纪念品id
     * @return Cart
     */
    Cart selectByUserIdAndSouvenirId(String userId, Integer souvenirId);

    /**
     * 删除用户的某一纪念品的购物车信息
     * @param userId 用户id
     * @param souvenirId 纪念品id
     * @return int
     */
    int deleteByUserIdAndSouvenirId(String userId, Integer souvenirId);
}
