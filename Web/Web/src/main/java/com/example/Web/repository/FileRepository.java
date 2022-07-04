package com.example.Web.repository;

import com.example.Web.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileModel, String> {

}
