package com.watermelon.wmclass.exception;

/**
 * @Description: 自定义异常类
 * @Author; Watermelon
 * @Date: 2019/3/22 20:22
 */
public class CustomException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常消息
     */
    private String msg;

    public CustomException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
