package com.example.Web.controller;

import com.example.Web.message.ResponseFile;
import com.example.Web.message.ResponseMessage;
import com.example.Web.model.FileModel;
import com.example.Web.model.Product;
import com.example.Web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
public class Controllerfile {
    @Autowired
    private ProductService service;

    @RequestMapping("/menuitem1")
    public String home() {
        return "detail";
    }

    @GetMapping(value = { "/products"})
    public List<Product> product(Model model){
        model.addAttribute("listproduct",service.listAll());
        return service.listAll() ;
    }
    //tạo mới product
    @PostMapping("/products/create")
    public ResponseEntity<ResponseMessage> createProduct(@RequestParam("id") Long id,
                                                         @RequestParam("content") String  content,
                                                         @RequestParam("name") String name,
                                                         @RequestParam("price1") int price1,
                                                         @RequestParam("quantity") int quantity,
                                                         @RequestParam("view") int view) {
        String mess="";
        try {
            Product p = new Product();
            p.setContent(content);
            p.setId(id);
            p.setName(name);
            p.setPrice1(price1);
            p.setQuantity(quantity);
            p.setView(view);
            service.save(p);
             mess = "Uploaded [" + p.getName()+"] thành công: ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(mess));
        } catch (Exception e) {
            mess = "Lỗi: " ;
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseMessage(mess));
        }
    }
    // xoá product
    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable Long id){
        String mess ="";
        try {
            service.delete(id);
            mess = "Đã xoá ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(mess));
        }catch (Exception e){
            mess = "Lỗi ";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(mess));
        }
    }
    //sửa product
    @PutMapping("/products/edit/{id}")
    public ResponseEntity<ResponseMessage> updateProduct(@RequestBody Product product, @PathVariable Long id){
        String mess = "";
        try {
            service.get(id);
            service.save(product);
            mess = "Đã cập nhật ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(mess));
        } catch (NoSuchElementException e) {
            mess = "Đã xoá ";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(mess));
        }
    }
   // lấy ra thông tin chi tiết product
    @GetMapping("/products/{id}")
    public Object get(@PathVariable Long id){
        String mess = "";
        try {
            Product p=service.get(id);
            return p;
        }catch (Exception e){
            mess= "Không tìm thấy";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(mess));
        }
    }
    //tìm kiếm
    @RequestMapping(path = {"/search"})
    public List<Product> search( Model model, @RequestParam("key") String key) {
        List<Product> list = service.getByKeyword(key);
         model.addAttribute("list", list);
        return list;
    }
    // upload file
    @PostMapping(path = {"/upload"})
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            //new file
            String pathf= "C:\\Users\\VUHONGTHIEN\\Desktop\\Web\\Web\\file\\" + file.getOriginalFilename();
            File path = new File(pathf);
            path.createNewFile();
            // input data
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            // encrypt
            String encryptContent = Base64.getEncoder().encodeToString(file.getBytes());
            //decrypt
            byte[] decryptContent = Base64.getDecoder().decode(encryptContent);
            //ghi file
            fileOutputStream.write(decryptContent);
            service.store(file,pathf);
            message = "tải file thành công " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "lỗi " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    // list file
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = service.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path(dbFile.getId())
                    .toUriString();
            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    // find file by id
    @RequestMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileModel fileDB = service.getfile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
    //kiểm tra toàn vẹn
    @PostMapping("/upload/update")
    public boolean checkSum(@RequestParam("fileUp") MultipartFile fileUp, @RequestParam("fileServer") MultipartFile fileServer) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            String encryptContent_fileUp = Base64.getEncoder().encodeToString(fileUp.getBytes());
            String encryptContent_fileServer = Base64.getEncoder().encodeToString(fileServer.getBytes());

            byte[] decryptContent_fileUp = Base64.getDecoder().decode(encryptContent_fileUp);
            byte[] decryptContent_fileServer = Base64.getDecoder().decode(encryptContent_fileServer);

            return Arrays.equals(decryptContent_fileUp, decryptContent_fileServer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
