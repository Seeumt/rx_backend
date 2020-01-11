package cn.seeumt.vo;

import cn.seeumt.enums.ResultCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO implements Serializable {

    private static final long serialVersionUID = -6721853439167521929L;

    private Integer code;
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

    public static ResultVO success(Object object,String msg) {
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


    public static ResultVO error(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("是吧");
        resultVO.setData(object);
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
