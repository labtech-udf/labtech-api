package com.labtech.events.files;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

  List<ArquivoDTO> findAllByExcluded(Boolean excluded);

}
