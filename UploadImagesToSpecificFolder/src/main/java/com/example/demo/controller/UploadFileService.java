package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

	@Value("${file.upload.location}")
	private String storeLocation;

	public String uploadFile(MultipartFile file) throws IOException {

		if (file.isEmpty() || file == null) {

			throw new RuntimeException("File is empty please enter valid file name !!!!");
		}

		String contentType = file.getContentType();
		String name = file.getName();
		long size = file.getSize();
		Resource resource = file.getResource();

		long copy = Files.copy(file.getInputStream(), Paths.get(storeLocation + File.separator + file.getName()),
				StandardCopyOption.REPLACE_EXISTING);

		return "File uploaded Successfully";
	}

}
