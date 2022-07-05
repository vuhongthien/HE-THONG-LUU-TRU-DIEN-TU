package vn.fis.logfile.vinasoy.service;

import org.springframework.stereotype.Service;
import vn.fis.logfile.vinasoy.model.Attachment;

import java.util.List;

@Service
public interface I_AttachmentService {
    List<Attachment> getAllAttachment();
    Attachment findById(Long id);


    List<Attachment> findByIdEmployee(String idEmployee);
    List<Attachment> findByIdTask(String idEmployee);

    List<Attachment> search(String keyword);


    Attachment getByNameInFolder(String name);

    List<Attachment> getAttachmentSend(List<Long> list);

    //public SharedAttachment checkIsShareForUser(Long idFile, Long idUser);
}
