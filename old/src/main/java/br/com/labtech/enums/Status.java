package br.com.labtech.enums;

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

  public String getDescricao() {
    return this.descricao;
  }
}
