package vn.fis.logfile.vinasoy.model;

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

    private String fileNameOriginal;

    private String typeOfFile;
    public static class Mapper {
        public static AttachmentDTO mapFromAttachmentEntity(Attachment attachment) {
            return AttachmentDTO.builder()
                    .id(attachment.getId())
                    .idEmployee(attachment.getIdEmployee())
                    .idTask(attachment.getIdTask())
                    .attachmentDateTime(attachment.getAttachmentDateTime())
                    .fileNameOriginal(attachment.getOriginalFileName())
                    .typeOfFile(attachment.getTypeOfFile())
                    .build();
        }
    }

}
