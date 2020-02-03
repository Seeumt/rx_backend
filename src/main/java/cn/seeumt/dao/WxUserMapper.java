package cn.seeumt.dao;

import cn.seeumt.dataobject.WxUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-10
 */
@Repository("wxUserMapper")
public interface WxUserMapper extends BaseMapper<WxUser> {


}
