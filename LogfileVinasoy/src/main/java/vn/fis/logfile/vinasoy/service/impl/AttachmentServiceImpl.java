package vn.fis.logfile.vinasoy.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.config.exception.type.AttachmentNotFoundException;
import vn.fis.logfile.vinasoy.mapper.AttachmentMapper;
import vn.fis.logfile.vinasoy.model.entity.Attachment;
import vn.fis.logfile.vinasoy.model.dto.AttachmentDTO;
import vn.fis.logfile.vinasoy.repository.AttachmentRepository;
import vn.fis.logfile.vinasoy.service.I_AttachmentService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static vn.fis.logfile.vinasoy.utils.FileNameUtils.*;
import static vn.fis.logfile.vinasoy.utils.FileHandlerUtils.*;

@Service
public class AttachmentServiceImpl implements I_AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private AttachmentMapper attachmentMapper;

    // PATH_LOCAL : F:/VINASOY/
    public static final String PATH_LOCAL = "F:" + File.separator + "VINASOY" + File.separator;


//    @Override
//    public List<Attachment> getAllAttachment() {
//        return attachmentRepository.findAll(Sort.by(Sort.Direction.DESC, "typeOfFile"));
//    }

    @Override
    public List<AttachmentDTO> findAll() {
        return attachmentRepository.findAll()
                .stream()
                .map(attachmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AttachmentDTO> pageFindAll(Pageable pageable) {
        return attachmentRepository.findAll(pageable).map(attachmentMapper::toDto);
    }

    @Override
    public AttachmentDTO findById(Long id) {
        Attachment attachment = attachmentRepository.findById(id).get();
        AttachmentDTO attachmentDTO = attachmentMapper.toDto(attachment);
        return attachmentDTO;
    }

    @Override
    @Transactional
    public List<AttachmentDTO> closeTicket(MultipartFile[] multipartFiles, MultipartFile[] commentFiles,
                                           String idEmployee, String moduleCode, String processCode,
                                           LocalDateTime ticketCreatedAt, String idTicket, String subject) {
        // pathLocal = F:/VINASOY/
        String CODEWF = "WF1";
        createFolder(PATH_LOCAL + CODEWF);
        createFolder(PATH_LOCAL + CODEWF + File.separator + idTicket);
        createFolder(PATH_LOCAL + CODEWF + File.separator + idTicket + File.separator + "Comments");

        String serverPath = CODEWF + File.separator + idTicket;
        List<AttachmentDTO> listMultipartFiles = addMultipartFilesSPro(multipartFiles, idEmployee, moduleCode, processCode,
                ticketCreatedAt, idTicket, subject, "TICKET", serverPath);

        serverPath = CODEWF + File.separator + idTicket + File.separator + "Comments";
        List<AttachmentDTO> listCommentFiles = addMultipartFilesSPro(commentFiles, idEmployee, moduleCode, processCode,
                ticketCreatedAt, idTicket, subject, "COMMENT", serverPath);

        List<AttachmentDTO> attachmentDTOList = new ArrayList<>();
        attachmentDTOList.addAll(listMultipartFiles);
        attachmentDTOList.addAll(listCommentFiles);
        return attachmentDTOList;
    }

    public List<AttachmentDTO> addMultipartFilesSPro(MultipartFile[] multipartFiles, String idEmployee,
                                                     String moduleCode, String processCode, LocalDateTime ticketCreatedAt, String idTicket,
                                                     String subject, String type, String serverPath) {
        List<AttachmentDTO> attachmentDTOList = new ArrayList<>();
        int STT = 1;
        for (MultipartFile file : multipartFiles) {
            // create attachment in database
            Attachment attachment = new Attachment();
            String serverFileName = createServerFileNameSPROUtils(moduleCode, processCode, ticketCreatedAt,
                    idTicket, subject, String.valueOf(STT));
            attachment.setServerFileName(serverFileName);
            attachment.setOriginalFileName(file.getOriginalFilename());
            attachment.setIdEmployee(idEmployee);
            attachment.setTypeOfAttachment(type); // TICKET / COMMENTS / TASK

            String typeOfFile = getTypeOfFile(attachment.getOriginalFileName());
            attachment.setTypeOfFile(typeOfFile);

            attachment.setAttachmentDateTime(LocalDateTime.now());
            attachment.setIdTaskTicket(idTicket);
            attachment.setTaskTicketCreatedAt(ticketCreatedAt);
            Attachment beginAttachment = attachmentRepository.save(attachment);
            Long idAttachment = attachment.getId();

            // pathLocal = F:/VINASOY/
            String serverFilePath = serverPath + File.separator + idAttachment + typeOfFile;
            beginAttachment.setServerFilePath(serverFilePath);
            Attachment attachmentAfterCreate = attachmentRepository.save(beginAttachment);

            // save attachment in localhost/ server
            String pathSaveFile = PATH_LOCAL + serverFilePath;
            saveFile(file, pathSaveFile);

            // return for viewer
            attachmentDTOList.add(attachmentMapper.toDto(attachmentAfterCreate));
        }
        return attachmentDTOList;
    }


    @Override
    @Transactional
    public List<AttachmentDTO> closeTask(MultipartFile[] multipartFiles, MultipartFile[] commentFiles,
                                         String idEmployee, String siteCode, String departmentCode,
                                         LocalDateTime taskCreatedAt, String boardName, String idTask,
                                         String subject) {
        // pathLocal = F:/VINASOY/
        String CODEWF = "WF2";
        createFolder(PATH_LOCAL + CODEWF);
        createFolder(PATH_LOCAL + CODEWF + File.separator + boardName);
        createFolder(PATH_LOCAL + CODEWF + File.separator + boardName + File.separator + idTask);
        createFolder(PATH_LOCAL + CODEWF + File.separator + boardName + File.separator + idTask +
                File.separator + "Comments");

        String serverPath = CODEWF + File.separator + boardName + File.separator + idTask;
        List<AttachmentDTO> listMultipartFiles = addMultipartFilesSFlash(multipartFiles, idEmployee, siteCode, departmentCode,
                taskCreatedAt, boardName, idTask, subject, "TASK", serverPath);

        serverPath = CODEWF + File.separator + boardName + File.separator + idTask + File.separator + "Comments";
        List<AttachmentDTO> listCommentFiles = addMultipartFilesSFlash(commentFiles, idEmployee, siteCode, departmentCode,
                taskCreatedAt, boardName, idTask, subject, "COMMENT", serverPath);

        List<AttachmentDTO> attachmentDTOList = new ArrayList<>();
        attachmentDTOList.addAll(listMultipartFiles);
        attachmentDTOList.addAll(listCommentFiles);
        return attachmentDTOList;
    }

    public List<AttachmentDTO> addMultipartFilesSFlash(MultipartFile[] multipartFiles, String idEmployee,
                                                       String siteCode, String departmentCode, LocalDateTime taskCreatedAt,
                                                       String boardName, String idTask, String subject, String type,
                                                       String serverPath) {
        List<AttachmentDTO> attachmentDTOList = new ArrayList<>();
        int STT = 1;
        for (MultipartFile file : multipartFiles) {
            // create attachment in database
            Attachment attachment = new Attachment();
            String serverFileName = createServerFileNameSFlashUtils(siteCode, departmentCode, taskCreatedAt,
                    boardName, idTask, subject, String.valueOf(STT));
            attachment.setServerFileName(serverFileName);
            attachment.setOriginalFileName(file.getOriginalFilename());
            attachment.setIdEmployee(idEmployee);
            attachment.setTypeOfAttachment(type); // TICKET / COMMENTS / TASK

            String typeOfFile = getTypeOfFile(attachment.getOriginalFileName());
            attachment.setTypeOfFile(typeOfFile);

            attachment.setAttachmentDateTime(LocalDateTime.now());
            attachment.setIdTaskTicket(idTask);
            attachment.setTaskTicketCreatedAt(taskCreatedAt);
            Attachment beginAttachment = attachmentRepository.save(attachment);
            Long idAttachment = attachment.getId();

            // pathLocal = F:/VINASOY/
            String serverFilePath = serverPath + File.separator + idAttachment + typeOfFile;
            beginAttachment.setServerFilePath(serverFilePath);
            Attachment attachmentAfterCreate = attachmentRepository.save(beginAttachment);

            // save attachment in localhost/ server
            String pathSaveFile = PATH_LOCAL + serverFilePath;
            saveFile(file, pathSaveFile);

            // return for viewer
            attachmentDTOList.add(attachmentMapper.toDto(attachmentAfterCreate));
        }
        return attachmentDTOList;
    }


    /**
     * Lưu ở server thì theo quy tác:  WF1 / ID_TICKET / ID_FILE
     * trên db thì id_file -> file name theo quy tắc
     */

    private void saveFile(MultipartFile multipartFile, String pathSaveFile) {
        try {
            String encryptContent = Base64.getEncoder().encodeToString(multipartFile.getBytes());
            File file = new File(pathSaveFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] decryptContent = Base64.getDecoder().decode(encryptContent);
            fileOutputStream.write(decryptContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createFolder(String pathDirectory) {
        // Path:            \VINASOY\WF1\idTicket
        try {
            File file = new File(pathDirectory);
            if (file.exists()) {
                return;
            }
            Path dir = Paths.get(pathDirectory);
            Files.createDirectories(dir);
        } catch (Exception e) {
        }

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
