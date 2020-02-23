package cn.seeumt.service;

import cn.seeumt.dataobject.ThirdPartyUser;
import cn.seeumt.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-10
 */
public interface ThirdPartyUserService {
    /**
     * 第三方注册登录
     * @param loginType 登录类型（weixin,weibo,qq）
     * @param thirdPartyUser 第三方登录实体类
     * @return ResultVO
     */
    ResultVO actionByLoginTypeAndThreePartyId(String loginType, cn.seeumt.form.ThirdPartyUser thirdPartyUser);

    /**
     * 注册用户
     * @param thirdPartyUser 第三方登录实体类
     * @return
     */
    ThirdPartyUser insert(ThirdPartyUser thirdPartyUser);

    /**
     * 保存/更新第三方用户
     * @param thirdPartyUser 第三方登录实体类
     * @return int
     */
    int save(ThirdPartyUser thirdPartyUser);
}
