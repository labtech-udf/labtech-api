package br.com.labtech.objDesenSus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjDesenSusRepository extends JpaRepository<ObjDesenSus, Long> {

  List<ObjDesenSus> findAllByExcluded(Boolean excluded);

}
