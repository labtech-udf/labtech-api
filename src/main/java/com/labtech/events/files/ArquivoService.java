package com.labtech.events.files;

import com.labtech.events.utils.GenericService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArquivoService extends GenericService<ArquivoDTO> {

  List<ArquivoDTO> findAll();

  ArquivoDTO create(MultipartFile file) throws Exception;

  Resource getFile(Long id) throws Exception;

  ArquivoDTO update(Long id, MultipartFile file) throws Exception;
}
