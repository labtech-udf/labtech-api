package br.com.labtech.arquivo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

  List<Arquivo> findAllByExcluded(Boolean excluded);

}
