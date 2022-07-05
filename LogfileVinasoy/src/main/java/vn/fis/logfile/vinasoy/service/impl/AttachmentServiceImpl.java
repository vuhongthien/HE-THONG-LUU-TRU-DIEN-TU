package com.fis.logfile.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.fis.logfile.vinasoy.exception.AttachmentNotFoundException;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.repository.AttachmentRepository;
import vn.fis.logfile.vinasoy.service.I_AttachmentService;

import java.util.List;

@Service
@Slf4j
public class AttachmentServiceImpl implements I_AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository ;

    @Override
    public List<Attachment> getAllAttachment() {
        return attachmentRepository.findAll(Sort.by(Sort.Direction.DESC, "typeOfFile"));
    }

    @Override
    public Attachment findById(Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow( ()->{
            try {
                throw new AttachmentNotFoundException(String.format("Not found attachment with id %s", id));
            } catch (AttachmentNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Service: Attachment: {}", attachment);
        return attachment;
    }


    @Override
    public List<Attachment> findByIdEmployee(String idEmployee) {
        return attachmentRepository.findByIdEmployee(idEmployee);
    }

    @Override
    public List<Attachment> findByIdTask(String idEmployee) {
        return null;
    }

    @Override
    public List<Attachment> search(String keyword) {
        return attachmentRepository.search(keyword);
    }


    @Override
    public Attachment getByNameInFolder(String name) {
        return attachmentRepository.getByNameInFolder(name);
    }

//    public SharedAttachment checkIsShareForUser(Long idFile, Long idUser) {
//        return attachmentRepository.checkIsShareForUser(idFile,idUser);
//    }
    @Override
    public List<Attachment> getAttachmentSend(List<Long> list) {
        return attachmentRepository.getAttachmentSend(list);
    }


}
