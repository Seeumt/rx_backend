package cn.seeumt.service.impl;

import cn.seeumt.dao.CategoryMapper;
import cn.seeumt.dataobject.Category;
import cn.seeumt.dataobject.Souvenir;
import cn.seeumt.dao.SouvenirMapper;
import cn.seeumt.enums.TipsBusiness;
import cn.seeumt.model.FcSouvenir;
import cn.seeumt.service.CategoryService;
import cn.seeumt.service.SouvenirService;
import cn.seeumt.vo.OrderDetailVO;
import cn.seeumt.vo.ResultVO;
import cn.seeumt.vo.SouvenirSimpleVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-28
 */
@Service
public class SouvenirServiceImpl extends ServiceImpl<SouvenirMapper, Souvenir> implements SouvenirService {

    @Autowired
    private SouvenirMapper souvenirMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryService categoryService;

    @Override
    public ResultVO getProductByKeywordCategory(String keywords, Integer categoryId, int pageNum, int pageSize, String orderBy) {
        return null;
    }

    /**
     * 更新或新增产品
     * @param souvenir
     * @param pics
     * @return
     */
    @Override
    public ResultVO saveOrUpdateProduct(Souvenir souvenir, MultipartFile[] pics) {
        if(souvenir != null){
            if(souvenir.getSouvenirId() != null){
                int rowCount = souvenirMapper.updateById(souvenir);
                if(rowCount > 0){
                    return ResultVO.success("更新产品信息成功");
                }
                return ResultVO.error("更新产品信息失败");
            }else{
                int rowCount = souvenirMapper.insert(souvenir);
                if(rowCount > 0){
                    return ResultVO.success("新增产品成功");
                }
                return ResultVO.success("新增产品失败");
            }
        }
        return ResultVO.error("新增或更新产品参数不正确");
    }

    @Override
    public List<SouvenirSimpleVO> listSimpleVO() {
        List<Souvenir> souvenirList = souvenirMapper.selectList(null);
        return souvenirList.stream().map(souvenir -> {
            SouvenirSimpleVO souvenirSimpleVO = new SouvenirSimpleVO();
            BeanUtils.copyProperties(souvenir, souvenirSimpleVO);
            souvenirSimpleVO.setNumber(1);
            return souvenirSimpleVO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<FcSouvenir> listFcSouvenirList() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("status", true);
        List<Category> categories = categoryMapper.selectList(wrapper);
        return categories.stream().map(category -> {
            FcSouvenir fcSouvenir = new FcSouvenir();
            Integer categoryId = category.getCategoryId();
            List<SouvenirSimpleVO> souvenirSimpleVOList = listSouvenirSimpleVoByCategoryId(categoryId);
            BeanUtils.copyProperties(category,fcSouvenir);
            fcSouvenir.setGoods(souvenirSimpleVOList);
            return fcSouvenir;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SouvenirSimpleVO> listSouvenirSimpleVoByCategoryId(Integer categoryId) {
        QueryWrapper<Souvenir> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        List<Souvenir> souvenirList = souvenirMapper.selectList(wrapper);
        return souvenirList.stream().map(souvenir -> {
            SouvenirSimpleVO souvenirSimpleVO = new SouvenirSimpleVO();
            BeanUtils.copyProperties(souvenir, souvenirSimpleVO);
            return souvenirSimpleVO;
        }).collect(Collectors.toList());
    }
//    @Override
//    public ResultVO getProductByKeywordCategory(String keywords,Integer categoryId,int pageNum,int pageSize,String orderBy){
//        if(StringUtils.isBlank(keywords) && categoryId == null){
//            return ResultVO.error(TipsBusiness.INVAILD_ARGUMENT.getCode(),TipsBusiness.INVAILD_ARGUMENT.getMsg());
//        }
//        List<Integer> categoryIdList = new ArrayList<>();
//
//        if(categoryId != null){
//            Category category = categoryMapper.selectById(categoryId);
//            if(category == null && StringUtils.isBlank(keywords)){
//                //没有该分类,且用户没提及还没有关键字,这个时候返回一个空的结果集,不报错
//                PageHelper.startPage(pageNum,pageSize);
//                List<SouvenirSimpleVO> souvenirSimpleVOList = Lists.newArrayList();
//                PageInfo<SouvenirSimpleVO> pageInfo = new PageInfo<>(souvenirSimpleVOList);
//                return ResultVO.success(pageInfo);
//            }
//            categoryIdList = categoryService.selectCategoryTreeByCategoryId(category.getCategoryId()).getData();
//        }
//        if(StringUtils.isNotBlank(keywords)){
//            keywords = new StringBuilder().append("%").append(keywords).append("%").toString();
//        }
//
//        PageHelper.startPage(pageNum,pageSize);
//        //排序处理
//        if(StringUtils.isNotBlank(orderBy)){
//            if(Const.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
//                String[] orderByArray = orderBy.split("_");
//                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
//            }
//        }
//        List<Product> productList = productMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keywords)?null:keywords,categoryIdList.size()==0?null:categoryIdList);
//
//        List<ProductListVo> productListVoList = Lists.newArrayList();
//        for(Product product : productList){
//            ProductListVo productListVo = assembleProductListVo(product);
//            productListVoList.add(productListVo);
//        }
//
//        PageInfo pageInfo = new PageInfo(productList);
//        pageInfo.setList(productListVoList);
//        return ResultVO.createBySuccess(pageInfo);
//    }
}
