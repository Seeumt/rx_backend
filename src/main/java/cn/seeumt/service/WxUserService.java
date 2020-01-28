package cn.seeumt.service;

import cn.seeumt.dataobject.WxUser;
import cn.seeumt.dto.MPWXUserInfoDTO;
import cn.seeumt.form.MPWXUserInfo;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-10
 */
public interface WxUserService {
    WxUser insert(MPWXUserInfo mpwxUserInfo,String openId,String sessionKey,String skey);

    WxUser selectByOpenId(String openId);

    int update(WxUser wxUser);

    ResultVO modifyUserInfo(MPWXUserInfoDTO mpwxUserInfoDTO);



}
