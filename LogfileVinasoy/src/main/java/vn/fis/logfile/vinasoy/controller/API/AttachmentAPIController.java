package vn.fis.logfile.vinasoy.controller.API;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.dto.AttachmentDTO;
import vn.fis.logfile.vinasoy.service.I_AttachmentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/attachment")

public class AttachmentAPIController {
    @Autowired
    I_AttachmentService attachmentService;

//    @GetMapping("/{pageNumber}/{pageSize}")
//    public Page<AttachmentDTO> findAll(@PathVariable(name = "pageNumber") Integer pageNumber
//            , @PathVariable(name = "pageSize") Integer pageSize) {
//        log.info("Response All Customer. PageNumber: {}, PageSize: {}", pageNumber, pageSize);
//        return attachmentService.findAll(PageRequest.of(pageNumber, pageSize));
//    }

    @GetMapping("/findAll")
    public List<AttachmentDTO> findAll() {
        return attachmentService.findAll();
    }

    @GetMapping("/findById/{id}")
    public AttachmentDTO findById(@RequestParam("id") Long id) {
        return attachmentService.findById(id);
    }

    @PostMapping(value = "/upload-close-ticket")
    public List<AttachmentDTO> closeTicket(@RequestParam("multipartFiles") MultipartFile[] multipartFiles,
                                           @RequestParam("commentFiles") MultipartFile[] commentFiles,
                                           @RequestParam("idEmployee") String idEmployee,
                                           @RequestParam("moduleCode") String moduleCode,
                                           @RequestParam("processCode") String processCode,
//                                           @RequestParam("ticketCreatedAt") LocalDateTime ticketCreatedAt,
                                           @RequestParam("idTicket") String idTicket,
                                           @RequestParam("subject") String subject
    ) {
        return attachmentService.closeTicket(multipartFiles, commentFiles, idEmployee, moduleCode, processCode,
                LocalDateTime.now(), idTicket, subject);

    }

    @PostMapping(value = "/upload-close-task")
    public List<AttachmentDTO> closeTask(@RequestParam("multipartFiles") MultipartFile[] multipartFiles,
                                           @RequestParam("commentFiles") MultipartFile[] commentFiles,
                                           @RequestParam("idEmployee") String idEmployee,
                                           @RequestParam("siteCode") String siteCode,
                                           @RequestParam("departmentCode") String departmentCode,
//                                           @RequestParam("ticketCreatedAt") LocalDateTime ticketCreatedAt,
                                           @RequestParam("boardName") String boardName,
                                           @RequestParam("idTask") String idTask,
                                           @RequestParam("subject") String subject
    ) {
        return attachmentService.closeTask(multipartFiles, commentFiles, idEmployee, siteCode, departmentCode,
                LocalDateTime.now(),boardName, idTask, subject);
    }

    @GetMapping(path = "/createFolder")
    public void createFolder(@RequestParam("path") String path) throws IOException {
        Path dir = Paths.get(path); // filePathServer
        Files.createDirectories(dir);
    }



//    @PostMapping("/upload")
//    public Page<AttachmentDTO>  uploadFile(@RequestParam("file") MultipartFile file_post, @RequestParam("idEmployee")
//    String idEmployee, @RequestParam("idTask") String idTask, @RequestParam("linkSaveFile") String linkSaveFile) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//
//        attachmentService.upload(file_post,idEmployee,idTask,linkSaveFile);
//
//        this.originalFileName = file_post.getOriginalFilename();
//        String fileName = regex(idTask) + "_" + regex(idEmployee) + "_" + this.originalFileName;
//        try {
//            this.fileUpload = new FileUpload();
//            this.encryptContent = Base64.getEncoder().encodeToString(file_post.getBytes());
//            this.fileUpload.setFileName(fileName);
//            this.fileUpload.setDateUpload(new Date());
//            this.fileUpload.setIdEmployee(regex(idEmployee));
//            this.fileUpload.setIdTask(regex(idTask));
//            this.fileUpload.setTypeOfFile(getTypeOfFile(fileName));
//            String filePathServer = this.linkSaveFile + regex(fileName);
//            this.file = new File(filePathServer);
//            FileOutputStream fileOutputStream = new FileOutputStream(this.file);
//
//            decryptContent = Base64.getDecoder().decode(this.encryptContent);
//            fileOutputStream.write(decryptContent);
//
//            this.fileUpload.setFilePathServer(filePathServer);
//            return fileRepository.save(this.fileUpload);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//    @PostMapping("/addUser")
//    public AppUser addUser(@RequestParam("userId") Long userId, @RequestParam("userName") String userName, @RequestParam("password") String password) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encryptPassword = bCryptPasswordEncoder.encode(password);
//
//    }


//    @PostMapping("/shareFolder")
//    public void doSth(@RequestParam("pathFolder") String pathFolder, @RequestParam("shareWho") Long shareWho) {
//        loop_sub_folder(pathFolder, shareWho);
//    }


}
