package cn.seeumt.controller;


import cn.seeumt.dataobject.Souvenir;
import cn.seeumt.service.SouvenirService;
import cn.seeumt.vo.ResultVO;
import cn.seeumt.vo.SouvenirSimpleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Seeumt
 * @since 2020-01-28
 */
@RestController
@RequestMapping("/souvenirs")
public class SouvenirController {
    @Autowired
    private SouvenirService souvenirService;

    @GetMapping("/")
    public ResultVO list() {
        return ResultVO.success(souvenirService.listSimpleVO());
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
