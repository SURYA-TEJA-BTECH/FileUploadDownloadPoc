package com.surya.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surya.entity.FileEntity;

public interface FileReposistry extends JpaRepository<FileEntity, Long> {

}
