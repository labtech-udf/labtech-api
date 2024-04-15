package com.labtech.events.constants.Enums;

import lombok.Getter;

@Getter
public enum Status {
  C("Em construção"),
  AV("Avaliação"),
  R("Recusado"),
  IN("Inativo"),
  F("Finalizado"),
  AP("Aprovado");

  private String descricao;

  Status(final String descricao) {
    this.descricao = descricao;
  }

}
