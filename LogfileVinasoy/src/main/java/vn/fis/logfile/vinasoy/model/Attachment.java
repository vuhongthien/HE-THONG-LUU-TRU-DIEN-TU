package vn.fis.logfile.vinasoy.model;

import lombok.*;

import javax.persistence.*;
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

    private Long idEmployee;

    private String idTask;

    private Date attachmentDateTime;

    private String fileNameOriginal; // vd: bao-cao

    private String fileNameInSever;  // vd: NV01-TASK01-bao-cao

    private String filePathInServer; //
    private String typeOfFile;


    //    custom method
    public boolean checkIsFolder() {
        if ( (this.typeOfFile).equals("folder") ) {
            return true;
        }
        return false;
    }
}
