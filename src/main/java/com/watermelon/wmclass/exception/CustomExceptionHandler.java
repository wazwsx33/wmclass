package com.watermelon.wmclass.exception;

import com.watermelon.wmclass.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 异常处理控制器
 * @Author; Watermelon
 * @Date: 2019/3/22 20:26
 */
@ControllerAdvice //辅助Controller，可以使用@ExceptionHandler、@InitBinder、@ModelAttribute中的方法
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData Handler(Exception e){
        if (e instanceof CustomException){
            CustomException customException = (CustomException) e;
            return JsonData.buildError(customException.getMsg(), customException.getCode());
        } else {
            return JsonData.buildError("全局异常，未知错误");
        }
    }
}
