package vn.fis.logfile.vinasoy.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
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

    private String idTask;

    private LocalDateTime attachmentDateTime;

    private String originalFileName; // vd: bao-cao

    private String serverFileName;  // vd: NV01-TASK01-bao-cao

    private String serverFilePath; //
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

    public String getTypeOfFile() {
        return typeOfFile;
    }

    public void setTypeOfFile(String typeOfFile) {
        this.typeOfFile = typeOfFile;
    }

    //    custom method
    public boolean checkIsFolder() {
        if ( (this.typeOfFile).equals("folder") ) {
            return true;
        }
        return false;
    }
}
