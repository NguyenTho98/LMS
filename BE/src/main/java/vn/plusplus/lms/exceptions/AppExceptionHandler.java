package vn.plusplus.lms.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(value = AppException.class)
    public Object handleAppException(HttpServletRequest request, AppException re)
            throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(re.getCode());
        errorResponse.setMessage(re.getMessage());
        RequestLog.error(request, re.getStatus().value(), errorResponse, re);
        return new ResponseEntity<>(errorResponse, re.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public Object handleException(HttpServletRequest request, Exception re)
            throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ErrorCode.GENERAL_ERROR.code());
        errorResponse.setMessage(re.getMessage());
        RequestLog.error(request, ErrorCode.GENERAL_ERROR.status().value(), errorResponse, re);
        return new ResponseEntity<>(errorResponse, ErrorCode.GENERAL_ERROR.status());
    }
}