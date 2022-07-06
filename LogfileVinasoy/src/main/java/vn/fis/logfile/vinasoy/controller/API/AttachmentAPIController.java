package vn.fis.logfile.vinasoy.controller.API;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.model.AttachmentDTO;
import vn.fis.logfile.vinasoy.service.I_AttachmentService;
import vn.fis.logfile.vinasoy.service.impl.AttachmentServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
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
//        log.info("Response Find All");
        return attachmentService.findAll();
    }

    @PostMapping("/upload")
    public AttachmentDTO upload (@RequestParam("file") MultipartFile file_post, @RequestParam("idEmployee")
        String idEmployee, @RequestParam("idTask") String idTask, @RequestParam("linkSaveFile") String linkSaveFile){
        return attachmentService.upload(file_post,idEmployee,idTask,linkSaveFile);
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
