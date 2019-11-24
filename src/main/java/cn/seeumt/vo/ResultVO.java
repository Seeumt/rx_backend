package cn.seeumt.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class ResultVO implements Serializable {

    private static final long serialVersionUID = -6721853439167521929L;

    private Integer code;
    private String msg;
    private Object data;


    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {

        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


    public static ResultVO error(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("是吧");
        resultVO.setData(object);
        return resultVO;
    }
}
