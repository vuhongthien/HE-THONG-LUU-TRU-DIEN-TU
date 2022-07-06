package vn.fis.logfile.vinasoy.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.fis.logfile.vinasoy.config.exception.type.AttachmentNotFoundException;

import java.time.LocalDateTime;

import static vn.fis.logfile.vinasoy.config.exception.Constant.ATTACHMENT_NOT_FOUND;


@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {AttachmentNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handlerAttachmentNotFoundException(AttachmentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(ATTACHMENT_NOT_FOUND)
                        .message(e.getMessage()).build());
    }

//    @ExceptionHandler(value = {MobileExistedException.class})
//    protected ResponseEntity<ErrorMessage> handlerMobileExistedException(MobileExistedException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(MOBILE_IS_EXISTED)
//                        .message(e.getMessage()).build());
//    }





}
