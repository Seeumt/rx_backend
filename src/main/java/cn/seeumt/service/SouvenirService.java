package cn.seeumt.service;

import cn.seeumt.dao.ShippingMapper;
import cn.seeumt.dataobject.Shipping;
import cn.seeumt.dataobject.Souvenir;
import cn.seeumt.vo.ResultVO;
import cn.seeumt.vo.SouvenirSimpleVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-28
 */
public interface SouvenirService extends IService<Souvenir> {

    ResultVO getProductByKeywordCategory(String keywords, Integer categoryId, int pageNum, int pageSize, String orderBy);
    ResultVO saveOrUpdateProduct(Souvenir souvenir, MultipartFile[] pics);
    List<SouvenirSimpleVO> listSimpleVO();

}
