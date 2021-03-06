package exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SpringMVC全局异常拦截处理器
 * @author Created by L.C.Y on 2018-8-28
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        MyException myException = null;
        if (e instanceof MyException) {
            myException = (MyException) e;
        } else {//如果该异常类型不是系统自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）
            myException = new MyException("未知错误");
        }
        return null;
    }
}
