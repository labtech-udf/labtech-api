package br.com.labtech.utils.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class LocalFileStorageService implements FileStorageService {

  private Path fileStorageLocation;

  public LocalFileStorageService(@Value("${storage.path}") String storagePath) {
    this.fileStorageLocation = Paths.get(storagePath).toAbsolutePath().normalize();
    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (Exception ex) {
      throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
    }
  }

  public void storeFile(String fileName, MultipartFile file) {
    try {
      Path targetLocation = this.fileStorageLocation.resolve(fileName);

//      Redimencionar tamanho do arquivo caso seja necesserio

//      InputStream is = new ByteArrayInputStream(file.getBytes());
//      BufferedImage bufferedImage = ImageIO.read(is);
//      ByteArrayOutputStream bos = new ByteArrayOutputStream();
//      if (bufferedImage.getWidth() > maxWidth ||
//        bufferedImage.getHeight() > maxHeight) {
//        BufferedImage imageResized = Scalr.resize(bufferedImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, maxWidth, maxHeight, Scalr.OP_ANTIALIAS);
//        String extension = FilenameUtils.getExtension(StringUtils.cleanPath(file.getOriginalFilename()));
//        ImageIO.write(imageResized, extension, bos);
//        is = new ByteArrayInputStream(bos.toByteArray());
//        Files.copy(is, targetLocation, StandardCopyOption.REPLACE_EXISTING);
//        imageResized.flush();
//      } else {
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//      }
    } catch (IOException ex) {
      throw new RuntimeException("Não foi possível salvar a foto " + file.getOriginalFilename() + ".", ex);
    }
  }

  public Resource loadFileAsResource(String fileName) throws Exception {
    try {
      Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists()) {
        return resource;
      } else {
        throw new FileNotFoundException("File not found " + fileName);
      }
    } catch (MalformedURLException ex) {
      throw new FileNotFoundException("File not found " + fileName);
    }
  }

  public void delete(String fileName) {
    try {
      Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
      Files.delete(filePath);
    } catch (IOException ex) {
      throw new RuntimeException("Falha ao excluir o arquivo " + fileName, ex);
    }
  }
}
