package com.artisan.common.aspect;

import com.artisan.common.exception.TokenException;
import com.artisan.pojo.vo.ResultBean;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;


/**
 * 全局异常处理切面
 * @author leeyom
 * @date 2017年10月19日 10:41
 */

@ControllerAdvice
@ResponseBody
public class ExceptionAspect {

    private static final Logger log = Logger.getLogger(ExceptionAspect.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultBean handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(400);
        resultBean.setMsg("Could not read json...");
        log.error("Could not read json...", e);
        return resultBean;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultBean handleValidationException(MethodArgumentNotValidException e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(400);
        resultBean.setMsg("参数检验异常！");
        log.error("参数检验异常！", e);
        return resultBean;
    }

    /**
     * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultBean handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(405);
        resultBean.setMsg("请求方法不支持！");
        log.error("请求方法不支持！", e);
        return resultBean;
    }

    /**
     * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResultBean handleHttpMediaTypeNotSupportedException(Exception e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(415);
        resultBean.setMsg("内容类型不支持！");
        log.error("内容类型不支持！", e);
        return resultBean;
    }

    /**
     * 401 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TokenException.class)
    public ResultBean handleTokenException(Exception e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(401);
        resultBean.setMsg("Token已失效");
        log.error("Token已失效", e);
        return resultBean;
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultBean handleException(Exception e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(500);
        resultBean.setMsg("内部服务器错误！");
        log.error("内部服务器错误！", e);
        return resultBean;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResultBean handleValidationException(ValidationException e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(400);
        resultBean.setMsg("参数验证失败！");
        log.error("参数验证失败！", e);
        return resultBean;
    }

}
