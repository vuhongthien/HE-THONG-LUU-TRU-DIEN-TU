package vn.fis.logfile.vinasoy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.entity.Attachment;
import vn.fis.logfile.vinasoy.model.dto.AttachmentDTO;

import java.time.LocalDateTime;
import java.util.List;


public interface I_AttachmentService {
    List<AttachmentDTO> findAll();

    Page<AttachmentDTO> pageFindAll(Pageable pageable);

    AttachmentDTO findById(Long customerId);

    public List<AttachmentDTO> closeTicket(MultipartFile[] multipartFiles, MultipartFile[] commentFiles,
                                           String idEmployee, String moduleCode, String processCode,
                                           LocalDateTime ticketCreatedAt, String idTicket, String subject);
    public List<AttachmentDTO> closeTask(MultipartFile[] multipartFiles, MultipartFile[] commentFiles,
                                         String idEmployee, String siteCode, String departmentCode,
                                         LocalDateTime taskCreatedAt, String boardName, String idTask,
                                         String subject);
}
