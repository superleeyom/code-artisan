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
        resultBean.setMsg("Parameter validation exception...");
        log.error("Parameter validation exception...", e);
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
        resultBean.setMsg("Request method not supported...");
        log.error("Request method not supported...", e);
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
        resultBean.setMsg("Content type not supported...");
        log.error("Content type not supported...", e);
        return resultBean;
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TokenException.class)
    public ResultBean handleTokenException(Exception e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(500);
        resultBean.setMsg("Token is invaild...");
        log.error("Token is invaild...", e);
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
        resultBean.setMsg("Internal Server Error...");
        log.error("Internal Server Error...", e);
        return resultBean;
    }

}
