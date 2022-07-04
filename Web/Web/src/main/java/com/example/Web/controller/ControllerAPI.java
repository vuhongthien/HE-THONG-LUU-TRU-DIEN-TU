package com.example.Web.controller;

import com.example.Web.message.ResponseFile;
import com.example.Web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

//@Controller
@Controller
public class ControllerAPI {
    @Autowired
    private ProductService service;
    //list file
    @GetMapping("/")
    public String getListFiles(Model model) {
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
        model.addAttribute("FL", files);
        return "index";
    }
    // list file menu1
    @GetMapping("/menu1")
    public String getListtopage(Model model) {
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
        model.addAttribute("FL", files);
        return "indexcontent/indexbody1";
    }
}
