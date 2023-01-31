package com.andy.medicab.dao;

import com.andy.medicab.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileInfoDao extends JpaRepository<FileInfo,Long> {
    FileInfo getFileInfoByNameAllIgnoreCase(String nAME);
}
