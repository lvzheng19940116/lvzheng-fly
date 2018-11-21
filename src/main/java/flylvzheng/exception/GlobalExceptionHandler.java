package flylvzheng.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {
     private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

     @ExceptionHandler(value = BindException.class)
     @ResponseBody
     public Response<String> bindExceptionErrorHandler(BindException ex) throws Exception {
          logger.error("bindExceptionErrorHandler info:{}",ex.getMessage());
          Response<String> r = new Response<>();
          StringBuilder sb = new StringBuilder();
          FieldError fieldError = ex.getFieldError();
          sb.append(fieldError.getDefaultMessage());
          r.setMsg(sb.toString());
          r.setCode(Code.FAILED);
          return r;
     }


    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Response<String> myExceptionErrorHandler(MyException ex) throws Exception {
        logger.error("myExceptionErrorHandler info:{}",ex.getMessage());
        Response<String> r = new Response<>();
        r.setMsg(ex.getMsg());
        r.setCode(ex.getCode());
        return r;
    }




}
