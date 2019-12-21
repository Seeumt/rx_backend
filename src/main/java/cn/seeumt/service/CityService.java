package cn.seeumt.service;



import cn.seeumt.dataobject.City;

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

    List<City> findByCityIds(List<String> cityIds);
}
