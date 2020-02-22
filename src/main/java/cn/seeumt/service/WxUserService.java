package cn.seeumt.service;

import cn.seeumt.dataobject.WxUser;
import cn.seeumt.dto.MpWxUserInfoDTO;
import cn.seeumt.form.MpWxUserInfo;
import cn.seeumt.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-10
 */
public interface WxUserService {
    WxUser insert(MpWxUserInfo mpwxUserInfo, String openId, String sessionKey, String skey);

    WxUser selectByOpenId(String openId);

    int update(WxUser wxUser);

    ResultVO modifyUserInfo(MpWxUserInfoDTO mpwxUserInfoDTO);



}
