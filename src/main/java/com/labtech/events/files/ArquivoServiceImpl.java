package com.labtech.events.files;

import com.labtech.events.utils.GenericServiceImpl;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArquivoServiceImpl extends GenericServiceImpl<Arquivo, ArquivoDTO> implements ArquivoService {

  private final ArquivoRepository repository;
  private final ArquivoMapper mapper;
  private final Path fileStorageLocation;
  private final Integer maxWidth;
  private final Integer maxHeight;

  public ArquivoServiceImpl(
    ArquivoRepository repository,
    ArquivoMapper mapper,
    @Value("${server.storagePath}") String storagePath,
    @Value("${image.maxWidth}") Integer maxWidth,
    @Value("${image.maxHeight}") Integer maxHeight
  ) {
    super(repository, mapper);
    this.repository = repository;
    this.mapper = mapper;
    this.maxWidth = maxWidth;
    this.maxHeight = maxHeight;
    this.fileStorageLocation = Paths.get(storagePath).toAbsolutePath().normalize();
    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (Exception ex) {
      throw new RuntimeException("Erro ao criar local de arquivos");
    }
  }

  //Get and Find's
  @Override
  public List<ArquivoDTO> findAll() {
    return this.repository.findAllByExcluded(Boolean.FALSE);
  }

  @Override
  public Resource getFile(Long id) throws Exception {
    Optional<ArquivoDTO> arquivo = findOneById(id);
    if (arquivo.isPresent()) {
      Arquivo file = mapper.toEntity(arquivo.get());
      return this.loadFileAsResource(file.getKey());
    }
    return null;
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

  // Create arquivo
  @Override
  public ArquivoDTO create(final MultipartFile file) throws Exception {
    Arquivo arquivo = setArquivo(file, new Arquivo());
    this.setStorage(arquivo.getKey(), file);
    return save(mapper.toDto(arquivo));
  }

  //Update arquivo
  @Override
  public ArquivoDTO update(final Long id, final MultipartFile file) throws Exception {
    Optional<ArquivoDTO> arquivo = findOneById(id);
    if (arquivo.isPresent()) {
      Arquivo newFile = setArquivo(file, mapper.toEntity(arquivo.get()));
      this.setStorage(newFile.getKey(), file);
      return save(mapper.toDto(newFile));
    }
    return null;
  }

  public Arquivo setArquivo(final MultipartFile file, final Arquivo arquivo) {
    arquivo.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
    arquivo.setSize(file.getSize());
    arquivo.setUid(arquivo.getUid() != null ? arquivo.getUid() : UUID.randomUUID());
    arquivo.setType(file.getContentType());
    arquivo.setKey(arquivo.getUid() +
      file.getOriginalFilename().substring(
        file.getOriginalFilename().lastIndexOf(".")
      ));
    return arquivo;
  }

  private void setStorage(final String key, final MultipartFile file) {
    try {
      Path targetLocation = this.fileStorageLocation.resolve(key);
      InputStream is = new ByteArrayInputStream(file.getBytes());
      BufferedImage bufferedImage = ImageIO.read(is);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      if (bufferedImage.getWidth() > this.maxWidth ||
        bufferedImage.getHeight() > this.maxHeight) {
        BufferedImage imageResized = Scalr.resize(bufferedImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, this.maxWidth, this.maxHeight, Scalr.OP_ANTIALIAS);
        String extension = String.valueOf(Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf("."));
        ImageIO.write(imageResized, extension, bos);
        is = new ByteArrayInputStream(bos.toByteArray());
        Files.copy(is, targetLocation, StandardCopyOption.REPLACE_EXISTING);
        imageResized.flush();
      } else {
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      }

    } catch (IOException ex) {
      throw new RuntimeException("Não foi possível salvar a foto " + key + ".", ex);
    }
  }

}
