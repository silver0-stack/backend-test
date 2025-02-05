package kr.co.polycube.backendtest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice /* 전역 컨트롤러의 역할을 하는 클래스에 지정. 예외처리와 같은 여러 컨트롤러에서 공통으로 사용되는 기능을 정의함.
 예외 처리, 모델 속성 추가, 바인딩 초기화, 전역 응답 설정 등의 작업 수행 */
public class GlobalExceptionHandler {

    /* GlobalExceptionHandler 클래스는 @RestControllerAdvice 를 사용하여 전역 예외 처리를 정의 */

    // 잘못된 요청이 들어오면 HTTP 400 상태의 {"reason": 실제사유}을 응답
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Map<String, String>>  handleIllegalArgumentException(IllegalArgumentException ex){
        Map<String, String> responseBody = Collections.singletonMap("reason", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseBody);
    }
    // 존재하지 않는 API 호출 시, HTTP 404 상태의 {"reason": 실제사유}을 응답
    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<Map<String, String>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, String> responseBody = Collections.singletonMap("reason", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseBody);
    }

}
