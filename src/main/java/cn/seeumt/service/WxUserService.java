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
    /**
     * 注册微信用户
     * @param mpwxUserInfo 微信原生实体类
     * @param openId openId
     * @param sessionKey sesion_key
     * @param skey skey
     * @return WxUser
     */
    WxUser insert(MpWxUserInfo mpwxUserInfo, String openId, String sessionKey, String skey);

    /**
     * 通过openId查询微信用户
     * @param openId openId
     * @return WxUser
     */
    WxUser selectByOpenId(String openId);

    /**
     * 更新微信对象
     * @param wxUser 微信原生实体类
     * @return int
     */
    int update(WxUser wxUser);

    /**
     * 修改微信用户信息
     * @param mpwxUserInfoDTO 微信实体类传输对象
     * @return ResultVO
     */
    ResultVO modifyUserInfo(MpWxUserInfoDTO mpwxUserInfoDTO);



}
