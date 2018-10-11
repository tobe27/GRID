package exception;

import model.ResponseData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;


/**
 * @ ControllerAdvice 是一个@Component，用于定义@ExceptionHandler
 * 当有更多异常时，直接在@ExceptionHandler内添加即可
 * @author Created by L.C.Y on 2018-9-28
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理MyException异常全局响应
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResponseData jsonExceptionHandler(HttpServletRequest request, MyException e) {
        return new ResponseData().fail(e.getMessage());
    }

}
