package cn.seeumt.vo;

import cn.seeumt.enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Seeumt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "通用返回类",description = "通用返回类")
public class ResultVO implements Serializable {

    private static final long serialVersionUID = -6721853439167521929L;
    @ApiModelProperty(example="0")
    private Integer code;
    @ApiModelProperty(example="成功")
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object data;




    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(Integer code, String msg, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO success(Object object, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg(msg);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO error(TipsFlash tipsFlash) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(tipsFlash.getCode());
        resultVO.setMsg(tipsFlash.getMsg());
        return resultVO;
    }

    public static ResultVO success(Tips tips) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(tips.getCode());
        resultVO.setMsg(tips.getMsg());
        return resultVO;
    }

    public static ResultVO success(Integer code,String msg,Boolean isOk) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(isOk);
        return resultVO;
    }

    public static ResultVO error(TipsBusiness tipsBusiness) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(tipsBusiness.getCode());
        resultVO.setMsg(tipsBusiness.getMsg());
        return resultVO;
    }


    public static ResultVO error(CodeEnum codeEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(codeEnum.getCode());
        resultVO.setMsg(codeEnum.getMsg());
        return resultVO;
    }



    public static ResultVO error(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("操作失败");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(Integer code,String msg,Boolean isOk) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(isOk);
        return resultVO;
    }

    public static ResultVO ok() {
        return ok("");
    }

    public static ResultVO ok(Object object) {
        return new ResultVO(ResultCode.SUCCESS, object);
    }

    public static ResultVO failure(ResultCode code) {
        return failure(code, "");
    }

    public static ResultVO failure(ResultCode code, Object o) {
        return new ResultVO(code, o);
    }

    public ResultVO (ResultCode resultCode) {
        setResultCode(resultCode);
    }

    public ResultVO (ResultCode resultCode,Object object) {
        setResultCode(resultCode);
        this.data = object;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
}
