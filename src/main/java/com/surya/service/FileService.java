package com.surya.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.surya.FileNotFoundException;
import com.surya.entity.FileEntity;
import com.surya.repo.FileReposistry;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileService {

	private final FileReposistry fileRepository;

	public FileEntity saveFile(MultipartFile file) throws IOException {

		FileEntity fileEntity = new FileEntity();
		fileEntity.setFileName(file.getOriginalFilename());
		fileEntity.setFileType(file.getContentType());
		fileEntity.setData(file.getBytes());

		System.out.println(fileEntity);

		FileEntity entity = fileRepository.save(fileEntity);

		return entity;

	}

	public FileEntity getFileById(Long id) {

		return fileRepository.findById(id).orElseThrow(() -> new FileNotFoundException("File not found"));
	}

}
