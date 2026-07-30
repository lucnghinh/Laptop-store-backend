package com.lucnghinh.laptop_store.service.impl;

import com.lucnghinh.laptop_store.exception.AppException;
import com.lucnghinh.laptop_store.exception.ErrorCode;
import com.lucnghinh.laptop_store.exception.ResourceNotFoundException;
import com.lucnghinh.laptop_store.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(".png", ".jpg", ".jpeg");
    private static final Set<String> ALLOWED_MIME_TYPES = Set.of("image/png", "image/jpeg");
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    @Override
    public String store(MultipartFile file){
        if (file == null || file.isEmpty()) {
            throw new AppException(ErrorCode.FILE_IS_EMPTY);
        }

        if( file.getSize() > MAX_FILE_SIZE) {
            throw new AppException(ErrorCode.FILE_TOO_LARGE);
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_MIME_TYPES.contains(contentType.toLowerCase())) {
            throw new AppException(ErrorCode.UNSUPPORTED_FILE_TYPE);
        }

        Path uploadPath = Paths.get("uploads");

        try {
            Files.createDirectories(uploadPath);


            String fileName = file.getOriginalFilename();

            if (fileName == null) {
                throw new AppException(ErrorCode.INVALID_FILE_NAME);
            }

            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex == -1||dotIndex == fileName.length() - 1) {
                throw new AppException(ErrorCode.INVALID_FILE_NAME);
            }

            String extension = fileName.substring(dotIndex);

            if (!ALLOWED_EXTENSIONS.contains(extension)) {
                throw new AppException(ErrorCode.UNSUPPORTED_FILE_TYPE);
            }

            String newFileName = UUID.randomUUID() + extension;

            Files.copy(file.getInputStream(), uploadPath.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);

            return newFileName;
        } catch (IOException e) {
            throw new AppException(ErrorCode.FILE_UPLOAD_FAILED);
        }
    }

    @Override
    public void delete(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return;
        }
        Path filePath = Paths.get("uploads", fileName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new ResourceNotFoundException(ErrorCode.FILE_DELETE_FAILED);
        }

        }

    }
