package br.com.labtech.arquivo;

import br.com.labtech.utils.GenericServiceImpl;
import br.com.labtech.utils.files.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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
  public ArquivoDTO findById(Long id) {
    Optional<Arquivo> optionalArquivo = this.repository.findById(id);
    if (optionalArquivo.isPresent()) {
      Arquivo arquivo = optionalArquivo.get();
      return this.mapper.toDto(arquivo);
    } else {
      return null;
    }
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
  public ArquivoDTO update(Long id, MultipartFile file) throws Exception {
    if (file == null || file.isEmpty()) {
      throw new IllegalArgumentException("O arquivo não foi fornecido para atualização.");
    }

    Arquivo arquivo = this.get(id);

    if (arquivo != null && arquivo.getKey() != null) {
      deleteExistingFile(arquivo.getKey());
    }

    updateArquivoDetails(arquivo, file);

    return super.save(mapper.toDto(arquivo));
  }

  private void deleteExistingFile(String key) {
    try {
      this.fileService.delete(key);
    } catch (Exception ex) {
      // Adicione um tratamento adequado para a exceção (pode ser uma falha silenciosa, log, etc.)
      throw new RuntimeException("Falha ao excluir o arquivo existente durante a atualização.", ex);
    }
  }

  private void updateArquivoDetails(Arquivo arquivo, MultipartFile file) {
    arquivo.setName(file.getOriginalFilename());
    arquivo.setSize(file.getSize());
    arquivo.setType(file.getContentType());
    String extension = arquivo.getName().substring(arquivo.getName().lastIndexOf("."));
    arquivo.setKey(arquivo.getId().toString() + extension);
    arquivo.setUrl(String.valueOf(arquivo.getId()));

    try {
      this.fileService.storeFile(arquivo.getKey(), file);
    } catch (Exception ex) {
      // Adicione um tratamento adequado para a exceção (pode ser uma falha silenciosa, log, etc.)
      throw new RuntimeException("Falha ao armazenar o novo arquivo durante a atualização.", ex);
    }
  }


  public Resource download(Long id) throws Exception {
    Arquivo arquivo = this.get(id);
    return fileService.loadFileAsResource(arquivo.getKey());
  }

  public Arquivo get(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException(String.format("Arquivo não encontrado - id: %s", id)));
  }
}
