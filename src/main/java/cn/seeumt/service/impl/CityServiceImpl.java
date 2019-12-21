package cn.seeumt.service.impl;

import cn.seeumt.dataobject.City;
import cn.seeumt.dao.CityMapper;
import cn.seeumt.dataobject.City;
import cn.seeumt.service.CityService;
import cn.seeumt.vo.CityVO;
import cn.seeumt.vo.CityVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<CityVO> findByCityIds(List<String> cityIds) {
        List<City> citys = cityMapper.selectBatchIds(cityIds);
        List<CityVO> cityVOS = new ArrayList<>();
        for (City city : citys) {
            CityVO cityVO = new CityVO();
            BeanUtils.copyProperties(city,cityVO);
            cityVOS.add(cityVO);
        }
        return cityVOS;

    }
}
