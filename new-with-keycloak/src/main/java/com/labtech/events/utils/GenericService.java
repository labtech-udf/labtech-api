package com.labtech.events.utils;

import java.util.List;
import java.util.Optional;

public interface GenericService<D extends AbstractEntityDTO> {

  D save(D d) throws Exception;

  Optional<D> findOneById(Long id);

  void delete(Long id) throws Exception;

  List<D> findAll();

  List<D> diffBetweenBasedOnId(List<D> aList, List<D> bList);


}
