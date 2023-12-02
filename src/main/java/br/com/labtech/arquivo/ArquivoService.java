package br.com.labtech.arquivo;

import br.com.labtech.utils.GenericService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ArquivoService extends GenericService<ArquivoDTO> {

  List<ArquivoDTO> findAll();

  ArquivoDTO insert(MultipartFile file) throws Exception;

  ArquivoDTO findById(Long id);

  Resource download(Long id) throws Exception;
}
