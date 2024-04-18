package com.labtech.events.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class GenericServiceImpl<E extends AbstractEntity, D extends AbstractEntityDTO> implements GenericService<D> {

  private JpaRepository<E, Long> repository;

  private final EntityMapper<D, E> mapper;

  public GenericServiceImpl(
    JpaRepository<E, Long> repository, EntityMapper<D, E> mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public D save(D d) throws Exception {
    return this.executeSave(d);
  }

  @Override
  public Optional<D> findOneById(Long id) {
    if (id == null) {
      return Optional.empty();
    }
    Optional<E> optional = this.repository.findById(id);
    if (optional.isPresent()) {
      E e = optional.get();
      return Boolean.FALSE.equals(e.getExcluded()) ? optional.map(this.mapper::toDto) : Optional.empty();
    }
    return Optional.empty();
  }

  @Override
  public void delete(Long id) throws Exception {
    Optional<D> optional = this.findOneById(id);
    this.executeDelete(optional);
  }

  D executeSave(D d) throws Exception {
    E e = this.mapper.toEntity(d);
    if (d.getId() == null) {
      e.setCreatedBy(d.getCreatedBy() != null ? d.getCreatedBy() : "admin");
      e.setCreated(LocalDateTime.now());
      e.setUpdatedBy(d.getUpdatedBy() != null ? d.getUpdatedBy() : "admin");
      e.setUpdated(LocalDateTime.now());
    } else {
      Optional<D> beforeOptional = this.findOneById(d.getId());
      if (beforeOptional.isEmpty()) {
        throw new Exception("The entity does not exist");
      }
      D before = beforeOptional.get();
      e.setCreated(before.getCreated());
      e.setCreatedBy(before.getCreatedBy());
      e.setUpdated(LocalDateTime.now());
      e.setUpdatedBy(d.getUpdatedBy());
    }
    this.repository.save(e);
    return this.mapper.toDto(e);
  }

  void executeDelete(Optional<D> optional) throws Exception {
    if (optional.isPresent()) {
      D d = optional.get();
      d.setExcluded(true);
      this.executeSave(d);
    }
  }


  abstract public List<D> findAll();

  @Override
  public List<D> diffBetweenBasedOnId(List<D> aList, List<D> bList) {
    if (bList == null) {
      return new ArrayList<>();
    }
    if (aList != null && aList.isEmpty()) {
      return bList;
    }
    if (aList == null) {
      return bList;
    }
    return aList.stream().filter(f -> isPresent(f, bList)).collect(Collectors.toList());
  }

  public Boolean isPresent(D candidate, List<D> comparationList) {
    if (candidate == null || comparationList == null || comparationList.isEmpty() || candidate.getId() == null) {
      return Boolean.FALSE;
    }
    for (D compared : comparationList) {
      if (candidate.getId().equals(compared.getId())) {
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }
}

