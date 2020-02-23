package cn.seeumt.service;



import cn.seeumt.dataobject.City;
import cn.seeumt.vo.CityVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
public interface CityService{

    /**
     * 通过城市id集合 批量查询城市信息
     * @param cityIds 城市id集合
     * @return
     */
    List<CityVO> findByCityIds(List<String> cityIds);
}
