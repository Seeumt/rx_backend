package cn.seeumt.utils;

import cn.seeumt.dao.UserMapper;
import cn.seeumt.dataobject.Love;
import cn.seeumt.dataobject.User;
import cn.seeumt.enums.TipsFlash;
import cn.seeumt.exception.TipsException;
import cn.seeumt.model.Thumber;
import cn.seeumt.service.LoveService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author Seeumt
 * @date 2019/12/11 20:36
 */
@Component
@Data
public class ThumberUtil {

    private static ThumberUtil thumberUtil;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoveService loveService;

    @PostConstruct
    public void init() {
        thumberUtil = this;
        thumberUtil.userMapper = userMapper;
        thumberUtil.loveService = this.loveService;
    }
    public static List<Thumber> allThumbers(String apiRootId) {
        List<Thumber> thumbers = new ArrayList<>();
        List<Love> loves = ThumberUtil.thumberUtil.loveService.selectByApiRootId(apiRootId);
        if (loves == null) {
            thumbers=null;
        }else {
        List idList = new ArrayList();
        List dateList = new ArrayList();
        List<Boolean> list = new ArrayList<>();
        for (Love love : loves) {
            idList.add(love.getUserId());
            dateList.add(love.getCreateTime());
            //作为点赞时间
            list.add(love.getStatus());
        }
        for (int i = 0; i < idList.toArray().length; i++) {
            Thumber thumber = new Thumber();
            User user = ThumberUtil.thumberUtil.userMapper.selectById(idList.toArray()[i].toString());
            if (user == null) {
                throw new TipsException(TipsFlash.ABNORMAL_THUMB);
            }
            user.setCreateTime((Date) dateList.toArray()[i]);
            BeanUtils.copyProperties(user, thumber);
            thumbers.add(thumber);
        }
    }
        return thumbers;

}}
