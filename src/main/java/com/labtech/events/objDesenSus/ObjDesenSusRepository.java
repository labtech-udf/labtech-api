package com.labtech.events.objDesenSus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjDesenSusRepository extends JpaRepository<ObjDesenSus, Long> {

  List<ObjDesenSus> findAllByExcludedOrderByIdAsc(Boolean excluded);

  List<ObjDesenSus> findAllByStatusOrderByIdAsc(Boolean status);


}
