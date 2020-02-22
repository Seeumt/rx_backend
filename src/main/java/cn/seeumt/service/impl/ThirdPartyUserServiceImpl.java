package cn.seeumt.service.impl;

import cn.seeumt.dataobject.ThirdPartyUser;
import cn.seeumt.dao.ThirdPartyUserMapper;
import cn.seeumt.dto.ThirdPartyUserDTO;
import cn.seeumt.enums.Tips;
import cn.seeumt.exception.TipsException;
import cn.seeumt.service.ThirdPartyUserService;
import cn.seeumt.utils.UuidUtil;
import cn.seeumt.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-10
 */
@Service
public class ThirdPartyUserServiceImpl implements ThirdPartyUserService {
    @Autowired
    private ThirdPartyUserMapper thirdPartyUserMapper;

    @Override
    public ResultVO actionByLoginTypeAndThreePartyId(String loginType, cn.seeumt.form.ThirdPartyUser thirdPartyUser) {
        QueryWrapper<ThirdPartyUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_type", loginType)
                .eq("third_party_id", thirdPartyUser.getThirdPartyId());
        thirdPartyUser.setLoginType(loginType);
        ThirdPartyUser thirdPartyUserDB = thirdPartyUserMapper.selectOne(wrapper);
        if (thirdPartyUserDB == null) {
            ThirdPartyUser thirdPartyUserDO = new ThirdPartyUser();
            thirdPartyUserDO.setFaceIcon(thirdPartyUser.getFaceIcon());
            thirdPartyUserDO.setNickName(thirdPartyUser.getNickName());
            thirdPartyUserDO.setThirdPartyId(thirdPartyUser.getThirdPartyId());
            thirdPartyUserDO.setLoginType(thirdPartyUser.getLoginType());
            thirdPartyUserDO.setTelephone(thirdPartyUser.getTelephone());
            thirdPartyUserDO.setUserId(UuidUtil.getUuid());
            thirdPartyUserDO.setCreateTime(new Date());
            thirdPartyUserDO.setLastVisitTime(new Date());
            thirdPartyUserDO.setSessionKey(null);
            thirdPartyUserDO.setSkey(null);
            ThirdPartyUser newThirdPartyUser = insert(thirdPartyUserDO);
            ThirdPartyUserDTO thirdPartyUserDTO = new ThirdPartyUserDTO();
            BeanUtils.copyProperties(newThirdPartyUser, thirdPartyUserDTO);
            return ResultVO.success(thirdPartyUserDTO, "亲，你终于来了！");
        } else {
            thirdPartyUserDB.setLastVisitTime(new Date());
            // TODO: 2020/1/10 skey
            thirdPartyUserDB.setSkey(UuidUtil.getUuid());
        }
        ThirdPartyUserDTO thirdPartyUserDTO = new ThirdPartyUserDTO();
        BeanUtils.copyProperties(thirdPartyUserDB, thirdPartyUserDTO);
        return ResultVO.success(thirdPartyUserDTO, "登录成功");
    }

    @Override
    public ThirdPartyUser insert(ThirdPartyUser thirdPartyUser) {
        int insert = thirdPartyUserMapper.insert(thirdPartyUser);
        if (insert > 0) {
            return thirdPartyUser;
        }
        throw new TipsException(Tips.THIRD_OAUTH_FAIL.getCode(), Tips.THIRD_OAUTH_FAIL.getMsg());
    }

    @Override
    public int save(ThirdPartyUser thirdPartyUser) {
        return thirdPartyUserMapper.updateById(thirdPartyUser);
    }


}

