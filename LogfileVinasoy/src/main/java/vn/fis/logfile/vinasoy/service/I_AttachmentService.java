package vn.fis.logfile.vinasoy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.model.AttachmentDTO;

import java.util.List;


public interface I_AttachmentService {
    List<AttachmentDTO> findAll();
    Page<AttachmentDTO> pageFindAll(Pageable pageable);

    Attachment findById(Long customerId);


    AttachmentDTO upload(MultipartFile uploadFile, String idEmployee, String idTask, String linkSaveFile);
}
