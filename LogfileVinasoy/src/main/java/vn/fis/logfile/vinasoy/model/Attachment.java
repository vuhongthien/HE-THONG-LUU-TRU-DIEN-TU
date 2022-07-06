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


    //    custom method
    public boolean checkIsFolder() {
        if ( (this.typeOfFile).equals("folder") ) {
            return true;
        }
        return false;
    }
}
