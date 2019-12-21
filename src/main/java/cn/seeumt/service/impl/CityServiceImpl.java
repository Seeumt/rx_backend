package cn.seeumt.service.impl;

import cn.seeumt.dataobject.City;
import cn.seeumt.dao.CityMapper;
import cn.seeumt.dataobject.Tag;
import cn.seeumt.service.CityService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> findByCityIds(List<String> cityIds) {
            List<City> citys = cityMapper.selectBatchIds(cityIds);
            return citys;

    }
}
