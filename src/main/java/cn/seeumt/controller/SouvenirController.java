package cn.seeumt.controller;


import cn.seeumt.dataobject.Souvenir;
import cn.seeumt.service.SouvenirService;
import cn.seeumt.vo.ResultVO;
import cn.seeumt.vo.SouvenirSimpleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 纪念品
 * @author Seeumt
 * @since 2020-01-28
 */
@Api(tags = {"纪念品"})
@RestController
@RequestMapping("/souvenirs")
public class SouvenirController {
    @Autowired
    private SouvenirService souvenirService;

    /**
     * 查询所有纪念品
     * @return ResultVO
     */
    @GetMapping("/")
    public ResultVO list() {
        return ResultVO.success(souvenirService.listSimpleVO());
    }

    /**
     * 查询所有类目以及其下所有纪念品
     * @return List<FcSouvenir>
     */
    @GetMapping("/all")
    public ResultVO listFcSouvenir() {
        return ResultVO.success(souvenirService.listFcSouvenirList());
    }


    @PostMapping("/")
    public ResultVO saveOrUpdate(Souvenir souvenir, @RequestPart("pics") MultipartFile[] pics){
        return souvenirService.saveOrUpdateProduct(souvenir,pics);
    }

    @DeleteMapping("/{souvenirId}")
    public ResultVO delete(@PathVariable Integer souvenirId){
        if (souvenirId == null) {
            ResultVO.error("参数不能为空");
        }
        boolean flag = souvenirService.removeById(souvenirId);
        if (flag) {
            return ResultVO.success("删除成功");
        }
        return ResultVO.error("删除失败");
    }






}
