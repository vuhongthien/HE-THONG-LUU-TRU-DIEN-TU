package vn.fis.logfile.vinasoy.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {
    private LocalDateTime timestamp;
    private String code;
    private String message;
}