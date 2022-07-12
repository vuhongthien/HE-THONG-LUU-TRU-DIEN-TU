package vn.fis.logfile.vinasoy.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "Attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String idEmployee;
    private String idTaskTicket;

    private LocalDateTime attachmentDateTime;

    private LocalDateTime taskTicketCreatedAt;

    private String originalFileName; // vd: bao-cao

    private String serverFileName;  // vd: NV01-TASK01-bao-cao

    private String serverFilePath; // WF1 / ID_TICKET / ID_FILE

    private String typeOfAttachment;

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

    public String getIdTaskTicket() {
        return idTaskTicket;
    }

    public void setIdTaskTicket(String idTaskTicket) {
        this.idTaskTicket = idTaskTicket;
    }

    public LocalDateTime getAttachmentDateTime() {
        return attachmentDateTime;
    }

    public void setAttachmentDateTime(LocalDateTime attachmentDateTime) {
        this.attachmentDateTime = attachmentDateTime;
    }

    public LocalDateTime getTaskTicketCreatedAt() {
        return taskTicketCreatedAt;
    }

    public void setTaskTicketCreatedAt(LocalDateTime taskTicketCreatedAt) {
        this.taskTicketCreatedAt = taskTicketCreatedAt;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getServerFileName() {
        return serverFileName;
    }

    public void setServerFileName(String serverFileName) {
        this.serverFileName = serverFileName;
    }

    public String getServerFilePath() {
        return serverFilePath;
    }

    public void setServerFilePath(String serverFilePath) {
        this.serverFilePath = serverFilePath;
    }


    public String getTypeOfAttachment() {
        return typeOfAttachment;
    }

    public void setTypeOfAttachment(String typeOfAttachment) {
        this.typeOfAttachment = typeOfAttachment;
    }

    public String getTypeOfFile() {
        return typeOfFile;
    }

    public void setTypeOfFile(String typeOfFile) {
        this.typeOfFile = typeOfFile;
    }

    //custom method
    public boolean checkIsFolder() {
        if ("folder".equals(typeOfAttachment)) {
            return true;
        }
        return false;
    }
}
