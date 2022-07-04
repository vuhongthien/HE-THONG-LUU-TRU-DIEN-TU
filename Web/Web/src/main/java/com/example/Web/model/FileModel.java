package com.example.Web.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity

@Table(name = "file")
public class FileModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String type;
    private String path;
    @Lob
    private byte[] data;
    public FileModel() {
    }
    public FileModel(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getData() {
        return data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
