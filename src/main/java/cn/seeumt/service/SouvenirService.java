package cn.seeumt.service;

import cn.seeumt.dao.ShippingMapper;
import cn.seeumt.dataobject.Shipping;
import cn.seeumt.dataobject.Souvenir;
import cn.seeumt.model.FcSouvenir;
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

    /**
     * 通过关键词查询商品（递归）
     * @param keywords 关键字
     * @param categoryId 类目id
     * @param pageNum 当前页数
     * @param pageSize 每页数量
     * @param orderBy 排序规则
     * @return ResultVO
     */
    ResultVO getProductByKeywordCategory(String keywords, Integer categoryId, int pageNum, int pageSize, String orderBy);

    /**
     * 保存或更新纪念品
     * @param souvenir 纪念品
     * @param pics 图片
     * @return ResultVO
     */
    ResultVO saveOrUpdateProduct(Souvenir souvenir, MultipartFile[] pics);

    /**
     * 展示所有纪念品
     * @return List<SouvenirSimpleVO>
     */
    List<SouvenirSimpleVO> listSimpleVO();


    /**
     * 查询所有类目以及其下所有纪念品
     * @return List<FcSouvenir>
     */
    List<FcSouvenir> listFcSouvenirList();

    /**
     * 通过目录Id查询其下所有商品List
     * @param categoryId 目录Id
     * @return List<SouvenirSimpleVO>
     */
    List<SouvenirSimpleVO> listSouvenirSimpleVoByCategoryId(Integer categoryId);

}
