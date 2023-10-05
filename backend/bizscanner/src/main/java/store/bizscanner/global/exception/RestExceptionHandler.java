package store.bizscanner.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleRestException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorResponse(errorCode.getHttpStatus(), errorCode.getMessage()),
                errorCode.getHttpStatus());
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = validMessageBuilder(ex);
        return new ResponseEntity<>(new ErrorResponse(status, message), status);
    }

    //Valid 메세지 만드는 메소드
    private String validMessageBuilder(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();

        fieldErrors.forEach(x -> sb.append(x.getField()).append("는 ").append(x.getDefaultMessage()).append(", "));
        //마지막 쉼표 제거, 마침표 추가
        sb.replace(sb.length()-2, sb.length(), ".");
        return sb.toString();
    }
}