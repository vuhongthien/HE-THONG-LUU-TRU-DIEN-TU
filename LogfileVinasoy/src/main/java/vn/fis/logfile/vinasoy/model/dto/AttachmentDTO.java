package vn.fis.logfile.vinasoy.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AttachmentDTO {
    private Long id;

    private String idEmployee;

    private String idTask;

    private LocalDateTime attachmentDateTime;

    private String originalFileName;

    private String typeOfFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public LocalDateTime getAttachmentDateTime() {
        return attachmentDateTime;
    }

    public void setAttachmentDateTime(LocalDateTime attachmentDateTime) {
        this.attachmentDateTime = attachmentDateTime;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getTypeOfFile() {
        return typeOfFile;
    }

    public void setTypeOfFile(String typeOfFile) {
        this.typeOfFile = typeOfFile;
    }
}
