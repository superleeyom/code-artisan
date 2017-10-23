package com.artisan.pojo.vo;

/**
 * restful API 接口统一响应值
 * @author leeyom
 * @date 2017年10月19日 10:57
 */
public class ResultBean {

    /**
     * 数据集
     */
    private Object data = null;
    /**
     * 返回信息
     */
    private String msg = "Request Success！";
    /**
     * 业务自定义状态码
     */
    private Integer code = 200;
    /**
     * 全局附加数据
     */
    private Object etxra = null;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getEtxra() {
        return etxra;
    }

    public void setEtxra(Object etxra) {
        this.etxra = etxra;
    }
}
