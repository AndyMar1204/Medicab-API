package com.andy.medicab.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "files_dir")
@Getter
@Setter
@ToString
public class FileInfo implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String name;
    private String url;
    public FileInfo() {
    }
    public FileInfo(String filename, String url) {
        setName(filename);
        setUrl(url);
    }
}
