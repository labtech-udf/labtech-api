package br.com.labtech.arquivo;

import br.com.labtech.utils.GenericServiceImpl;
import br.com.labtech.utils.files.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArquivoServiceImpl extends GenericServiceImpl<Arquivo, ArquivoDTO> implements ArquivoService {

  private final ArquivoRepository repository;

  private final ArquivoMapper mapper;

  private final FileStorageService fileService;

  public ArquivoServiceImpl(ArquivoRepository repository, ArquivoMapper mapper, FileStorageService fileService) {
    super(repository, mapper);
    this.repository = repository;
    this.mapper = mapper;
    this.fileService = fileService;
  }

  @Override
  public List<ArquivoDTO> findAll() {
    return this.mapper.toDto(this.repository.findAllByExcluded(Boolean.FALSE));
  }

  @Override
  public ArquivoDTO insert(MultipartFile file) throws Exception {
    ArquivoDTO arquivo = new ArquivoDTO();
    arquivo.setName(file.getOriginalFilename());
    arquivo.setSize(file.getSize());
    arquivo.setType(file.getContentType());
    arquivo = super.save(arquivo);
    String extension = arquivo.getName().substring(arquivo.getName().lastIndexOf("."));
    arquivo.setKey(arquivo.getId().toString() + extension);
    arquivo.setUrl(String.valueOf(arquivo.getId()));
    this.fileService.storeFile(arquivo.getKey(), file);
    super.save(arquivo);
    return arquivo;
  }

  @Override
  public ArquivoDTO findById(Long id) {
    Optional<Arquivo> optionalArquivo = this.repository.findById(id);
    if (optionalArquivo.isPresent()) {
      Arquivo arquivo = optionalArquivo.get();
      return this.mapper.toDto(arquivo);
    } else {
      return null;
    }
  }

  public Resource download(Long id) throws Exception {
    Arquivo arquivo = get(id);
    return fileService.loadFileAsResource(arquivo.getKey());
  }

  public Arquivo get(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException(String.format("Arquivo não encontrado - id: %s", id)));
  }
}
