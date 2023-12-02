package br.com.labtech.utils.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileStorageService {

  void storeFile(String fileName, MultipartFile file);

  Resource loadFileAsResource(String fileName) throws Exception;

}
