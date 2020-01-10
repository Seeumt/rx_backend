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
    ResultVO actionByLoginTypeAndThreePartyId(String loginType, cn.seeumt.form.ThirdPartyUser thirdPartyUser);

    ThirdPartyUser insert(ThirdPartyUser thirdPartyUser);

    int save(ThirdPartyUser thirdPartyUser);
}
