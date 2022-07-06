package vn.fis.logfile.vinasoy.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.config.exception.type.AttachmentNotFoundException;
import vn.fis.logfile.vinasoy.mapper.AttachmentMapper;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.model.AttachmentDTO;
import vn.fis.logfile.vinasoy.repository.AttachmentRepository;
import vn.fis.logfile.vinasoy.service.I_AttachmentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static vn.fis.logfile.vinasoy.utils.FileHandlerUtils.createServerFileNameUtils;
import static vn.fis.logfile.vinasoy.utils.FileHandlerUtils.regexRemoveSpecialCharacter;

@Service
//@Slf4j
public class AttachmentServiceImpl implements I_AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private AttachmentMapper attachmentMapper;
//    @Override
//    public List<Attachment> getAllAttachment() {
//        return attachmentRepository.findAll(Sort.by(Sort.Direction.DESC, "typeOfFile"));
//    }

    @Override
    public List<AttachmentDTO> findAll() {
//        log.info("Query all attachment");
        return attachmentRepository.findAll()
                .stream()
                .map(attachmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AttachmentDTO> pageFindAll(Pageable pageable) {
//        log.info("Query all attachment: PageNumber: {}, PageSize: {}", pageable.getPageNumber(), pageable.getPageSize());
        return attachmentRepository.findAll(pageable).map(attachmentMapper::toDto);
    }

    @Override
    public Attachment findById(Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> {
            try {
                throw new AttachmentNotFoundException(String.format("Not found attachment with id %s", id));
            } catch (AttachmentNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
//        log.info("Service: Attachment: {}", attachment);
        return attachment;
    }

    @Override
    @Transactional
    public AttachmentDTO upload(MultipartFile uploadFile, String idEmployee, String idTask, String linkSaveFile) {
        String serverFileName = createServerFileNameUtils(idEmployee, idTask, uploadFile.getOriginalFilename());
        String serverFilePath = linkSaveFile + "\\" + regexRemoveSpecialCharacter(serverFileName);
        Attachment attachment = new Attachment();
        attachment.setServerFilePath("");
        attachmentRepository.save(attachment);
//        log.info("Upload Attachment: {}", attachment);
        return attachmentMapper.toDto(attachment);
    }

//    @Override
//    public Page<AttachmentDTO> create(CreateCustomerDTO createCustomerDTO, Pageable pageable) {
//        Attachment customer = Attachment.builder()
//                .name(createCustomerDTO.getName())
//                .mobile(createCustomerDTO.getMobile())
//                .address(createCustomerDTO.getAddress())
//                .build();
//        customerRepo.save(customer);
//        log.info("Saved Customer: {}", customer);
//        return findAll(pageable);
//    }


}
