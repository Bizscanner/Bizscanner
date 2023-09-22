package store.bizscanner.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @RequiredArgsConstructor
public enum ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    REPORT_RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Report Resource not exists"),
    REQUEST_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Request is not allowed"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    RECOMMEND_JCATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "추천할 업종이 없습니다"),
    RECOMMEND_CAREA_NOT_FOUND(HttpStatus.NOT_FOUND, "추천할 상권이 없습니다"),
    SCRAP_NOT_FOUND(HttpStatus.NOT_FOUND, "Scrap Resource not exists");

    private final HttpStatus httpStatus;
    private final String message;
}
