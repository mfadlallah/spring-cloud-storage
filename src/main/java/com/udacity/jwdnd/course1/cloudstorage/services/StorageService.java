package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {

	String store(MultipartFile file, int userid);

	List<File> loadAll(int userId);

	File loadFile(int fileId);

	void deleteFile(int filId, int userId);

}