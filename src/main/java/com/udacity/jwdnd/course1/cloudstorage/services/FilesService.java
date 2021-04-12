package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FilesService implements StorageService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public String store(MultipartFile file, int userid) {
        try {
            if (file.isEmpty()) {
                return "Please pick a file to upload";
            }

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (this.fileMapper.getFilesByName(fileName) != null) {
                return "This file uploaded before";
            }
            File fileModel = new File(userid,
                    fileName,
                    file.getContentType(),
                    String.valueOf(file.getSize()),
                    file.getBytes());

            fileMapper.insert(fileModel);

        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
        return "";
    }

    @Override
    public List<File> loadAll(int userId) {
        return fileMapper.getFilesByUserId(userId);
    }

    @Override
    public File loadFile(int fileId) {
        return fileMapper.getFilesById(fileId);
    }

    @Override
    public void deleteFile(int fileId) {
        fileMapper.deleteFileByFileId(fileId);
    }

    public List<File> getFilesByUserId(int userId) {
        return fileMapper.getFilesByUserId(userId);
    }
}
