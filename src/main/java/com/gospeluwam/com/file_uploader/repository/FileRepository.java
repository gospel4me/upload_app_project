package com.gospeluwam.com.file_uploader.repository;

import com.gospeluwam.com.file_uploader.models.CustomFileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileRepository extends JpaRepository<CustomFileType,Integer> {
}
