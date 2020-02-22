package cn.seeumt.handler;

import cn.seeumt.exception.TipsException;
import cn.seeumt.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Seeumt
 */
@ControllerAdvice
public class TipsExceptionHandler {
    @ExceptionHandler(value = TipsException.class)
    @ResponseBody
    public ResultVO handlerTipsException(TipsException e) {
        return ResultVO.error(e.getCode(), e.getMessage());
    }
}
