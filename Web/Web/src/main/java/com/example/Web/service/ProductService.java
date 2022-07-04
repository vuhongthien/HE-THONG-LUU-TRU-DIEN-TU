package com.example.Web.service;

import com.example.Web.model.FileModel;
import com.example.Web.model.Product;
import com.example.Web.repository.FileRepository;
import com.example.Web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repo;
    @Autowired
    private FileRepository repof;
    public List<Product> listAll() {
        return repo.findAll();
    }
    public void save(Product product) {
        repo.save(product);
    }
    public Product get(Long id) {
        return repo.findById(id).get();
    }
    public void delete(Long id){
        repo.deleteById(id);
    }
    public List<Product> getByKeyword(String key){
        return repo.findByKey(key);
    }
    public FileModel store(MultipartFile file, String path) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileModel file1 = new FileModel(fileName, file.getContentType(), file.getBytes());
        file1.setPath(path);
        return repof.save(file1);
    }
    public FileModel getfile(String id){
        return repof.findById(id).get();
    }
    public Stream<FileModel> getAllFiles() {
        return repof.findAll().stream();
    }



}
