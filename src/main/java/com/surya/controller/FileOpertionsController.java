package com.surya.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.surya.entity.FileEntity;
import com.surya.service.FileService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FileOpertionsController {

	private FileService fileService;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {

		FileEntity fileEntity = fileService.saveFile(file);

		return new ResponseEntity<>(
				"File uploded sucessfully " + "File id is " + fileEntity.getId(),
				HttpStatus.OK);
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {

		FileEntity file = fileService.getFile(id);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFileName() + "\"")
				.body(file.getData());

	}

}
