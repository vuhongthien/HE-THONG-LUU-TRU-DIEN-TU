//package vn.fis.logfile.vinasoy.controller;
//
//
//import com.fis.logfile.model.Attachment;
//import com.fis.logfile.model.SharedAttachment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import vn.fis.logfile.vinasoy.repository.AttachmentRepository;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Base64;
//
//@RestController
//@RequestMapping("/api/attachment")
//public class AttachmentAPIController {
//
//    @Autowired
//    AttachmentRepository attachmentRepository;
//
//
//    @PostMapping("/upload/update")
//    public boolean checkSum(@RequestParam("fileUp") MultipartFile fileUp, @RequestParam("fileServer") MultipartFile fileServer) {
//        try {
//            String encryptContent_fileUp = Base64.getEncoder().encodeToString(fileUp.getBytes());
//            String encryptContent_fileServer = Base64.getEncoder().encodeToString(fileServer.getBytes());
//
//            byte[] decryptContent_fileUp = Base64.getDecoder().decode(encryptContent_fileUp);
//            byte[] decryptContent_fileServer = Base64.getDecoder().decode(encryptContent_fileServer);
//
//            return Arrays.equals(decryptContent_fileUp, decryptContent_fileServer);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    // Resource[] resources = resourcePatternResolver.getResources("/mydir/*.txt")
//    @GetMapping("/getFromResources")
//    public File[] getFromResources(@RequestParam("path") String path) throws IOException {
//        File f = new File(path);
//        File[] filesList = f.listFiles();
//
//        return filesList;
//    }
//
//    @GetMapping("/checkIsFolder")
//    public boolean checkIsFolder(@RequestParam("path") String typeOfFile) {
//        if (typeOfFile.equals("folder")) {
//            return true;
//        }
//        return false;
//    }
//
////    @PostMapping("/addUser")
////    public AppUser addUser(@RequestParam("userId") Long userId, @RequestParam("userName") String userName, @RequestParam("password") String password) {
////        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        String encryptPassword = bCryptPasswordEncoder.encode(password);
////
////    }
//
//
//    @PostMapping("/shareFolder")
//    public void doSth(@RequestParam("pathFolder") String pathFolder, @RequestParam("shareWho") Long shareWho) {
//        loop_sub_folder(pathFolder, shareWho);
//    }
//
//    public void loop_sub_folder(String pathFolder, Long shareWho) {
//        File f = new File(pathFolder);
//        Attachment attachment = attachmentRepository.getByNameInFolder(f.getName());
//        SharedAttachment sharedAttachment = new SharedAttachment();
//        sharedAttachment.setIdAttachment(attachment.getId());
//        sharedAttachment.setIdUser(shareWho);
//        sharedAttachmentRepository.save(sharedAttachment);
//        File[] filesList = f.listFiles();
//        for (int i = 0; i < filesList.length; i++) {
//            String fileNameInFolder = filesList[i].getName(); // lấy tên file ở server
//            Attachment attachment_parent = attachmentRepository.getByNameInFolder(fileNameInFolder); // lấy ra các thuộc tính của file dựa theo name
//            if (attachment_parent.getTypeOfFile().equals("folder")) {
//                loop_sub_folder(attachment_parent.getFilePathServer(), shareWho);
//            } else {
//                SharedAttachment sharedAttachment_sub = new SharedAttachment();
//                sharedAttachment_sub.setIdAttachment(attachment_parent.getId());
//                sharedAttachment_sub.setIdUser(shareWho);
//                sharedAttachmentRepository.save(sharedAttachment_sub);
//            }
//        }
//    }
//
//}
